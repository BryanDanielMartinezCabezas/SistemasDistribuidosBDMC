using System;
using System.Collections.Generic;
using System.Linq;
using System.Net.Http;
using System.Windows.Forms;
using Newtonsoft.Json;
using RabbitMQ.Client;
using System.Text;

namespace AplicacionPago
{
    public partial class Form1 : Form
    {
        private HttpClient http = new HttpClient();
        private List<dynamic> facturas = new List<dynamic>();

        // RabbitMQ - Configuración para versión 7.x
        private const string QUEUE_NAME = "colaPagos";

        public Form1()
        {
            InitializeComponent();
        }

        private async void btnBuscar_Click(object sender, EventArgs e)
        {
            try
            {
                string ci = txtCI.Text.Trim();
                facturas.Clear();

                // CESSA
                try
                {
                    var cessa = await http.GetStringAsync($"http://localhost:8000/api/facturas/{ci}");
                    var cessaList = JsonConvert.DeserializeObject<dynamic>(cessa);
                    foreach (var f in cessaList)
                        if (f.estado == "Pendiente")
                            facturas.Add(new { id = (int)f.id, ci = (string)f.ci, monto = (double)f.monto, estado = (string)f.estado, empresa = "CESSA" });
                }
                catch { }

                // ELAPAS
                try
                {
                    var elapas = await http.GetStringAsync($"http://localhost:3000/facturas/{ci}");
                    var elapasList = JsonConvert.DeserializeObject<dynamic>(elapas);
                    foreach (var f in elapasList)
                        if (f.estado == "Pendiente")
                            facturas.Add(new { id = (int)f.id, ci = (string)f.ci, monto = (double)f.monto, estado = (string)f.estado, empresa = "ELAPAS" });
                }
                catch { }

                // ENTEL
                try
                {
                    var query = $"{{\"query\":\"{{ facturasPorCI(ci: \\\"{ci}\\\") {{ id ci monto estado }} }}\"}}";
                    var content = new StringContent(query, System.Text.Encoding.UTF8, "application/json");
                    var entelResponse = await http.PostAsync("http://localhost:4000/graphql", content);
                    var entelResult = JsonConvert.DeserializeObject<dynamic>(await entelResponse.Content.ReadAsStringAsync());
                    foreach (var f in entelResult.data.facturasPorCI)
                        facturas.Add(new { id = (int)f.id, ci = (string)f.ci, monto = (double)f.monto, estado = (string)f.estado, empresa = "ENTEL" });
                }
                catch { }

                lblFacturas.Text = facturas.Count > 0 ?
                    string.Join("\n", facturas.Select(f => $"{f.id} | {f.empresa} | ${f.monto} | {f.estado}")) :
                    "Sin facturas";
            }
            catch (Exception ex)
            {
                MessageBox.Show(ex.Message);
            }
        }

        private async void btnPagar_Click(object sender, EventArgs e)
        {
            try
            {
                int id = int.Parse(txtPagar.Text);
                string empresa = txtEmpresa.Text.ToUpper();
                var factura = facturas.FirstOrDefault(f => f.id == id && f.empresa == empresa);

                if (factura == null) { MessageBox.Show("Factura no encontrada"); return; }

                bool ok = false;
                switch (empresa)
                {
                    case "CESSA":
                        ok = (await http.PutAsync($"http://localhost:8000/api/facturas/{id}", null)).IsSuccessStatusCode;
                        break;
                    case "ELAPAS":
                        ok = (await http.PutAsync($"http://localhost:3000/facturas/pagar/{id}", null)).IsSuccessStatusCode;
                        break;
                    case "ENTEL":
                        var mutation = $"{{\"query\":\"mutation {{ pagarFactura(id: {id}) {{ id estado }} }}\"}}";
                        var content = new StringContent(mutation, System.Text.Encoding.UTF8, "application/json");
                        ok = (await http.PostAsync("http://localhost:4000/graphql", content)).IsSuccessStatusCode;
                        break;
                }

                MessageBox.Show(ok ? "Pago exitoso" : "Error en pago");

               
                if (ok)
                {
                    EnviarNotificacionRabbitMQ(factura);
                    btnBuscar_Click(sender, e);
                }
            }
            catch (Exception ex)
            {
                MessageBox.Show(ex.Message);
            }
        }

       
        private async void EnviarNotificacionRabbitMQ(dynamic factura)
        {
            try
            {
                var factory = new ConnectionFactory { HostName = "localhost" };

                
                using var connection = await factory.CreateConnectionAsync();
                using var channel = await connection.CreateChannelAsync();

               
                await channel.QueueDeclareAsync(queue: QUEUE_NAME,
                                              durable: false,
                                              exclusive: false,
                                              autoDelete: false,
                                              arguments: null);

                
                var mensaje = JsonConvert.SerializeObject(new
                {
                    operacion = "PAGO_EXITOSO",
                    factura_id = factura.id,
                    empresa = factura.empresa,
                    ci = factura.ci,
                    monto = factura.monto,
                    fecha_pago = DateTime.Now
                });

                
                var body = Encoding.UTF8.GetBytes(mensaje);

                
                await channel.BasicPublishAsync(exchange: "",
                                              routingKey: QUEUE_NAME,
                                              body: body);

                Console.WriteLine("Mensaje enviado a RabbitMQ");
            }
            catch (Exception ex)
            {
                Console.WriteLine($"Error enviando a RabbitMQ: {ex.Message}");
                
            }
        }
    }
}