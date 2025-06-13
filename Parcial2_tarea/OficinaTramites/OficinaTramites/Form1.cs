using System.Net.Http;
using System.Text.Json;
using System.Text;
using rsSegip;
using System.Net;

namespace OficinaTramites
{
    public partial class Form1 : Form
    {
        public Form1()
        {
            InitializeComponent();
        }

        private async void btnEnviar_Click(object sender, EventArgs e)
        {
            var clienteSegip = new wsSegipSoapClient(wsSegipSoapClient.EndpointConfiguration.wsSegipSoap12);

            string ci = txtCi.Text.Trim();
            string nombres = txtNombres.Text.Trim();
            string primerApellido = txtPrimerApellido.Text.Trim();
            string segundoApellido = txtSegundoApellido.Text.Trim();
            string titulo = txtTitulo.Text.Trim();

            lblNombreControl.Text = " Verificando en SEGIP...";

            bool existe = clienteSegip.VerificarDatos(ci);

            if (!existe)
            {
                lblNombreControl.Text = " CI no válido en SEGIP.";
                return;
            }

            var persona = clienteSegip.ObtenerDatos(ci);

            if (persona == null)
            {
                lblNombreControl.Text = "Persona no encontrada en SEGIP.";
                return;
            }

            if (!(persona.Nombres.Equals(nombres, StringComparison.OrdinalIgnoreCase) &&
                persona.PrimerApellido.Equals(primerApellido, StringComparison.OrdinalIgnoreCase) &&
                persona.SegundoApellido.Equals(segundoApellido, StringComparison.OrdinalIgnoreCase)))
            {
                lblNombreControl.Text = "Los datos no coinciden con SEGIP.";
                return;
            }

            lblNombreControl.Text = "Verificado en SEGIP. Consultando SEDUCA...";   

            // GRAPHQL – Consultar a SEDUCA si es bachiller

            var httpClient = new HttpClient();
            var graphqlQuery = new
            {
                query = @"query {
            persona(CI: """ + ci + @""") {
                CI
                Nombres
                PrimerApellido
                SegundoApellido
                esBachiller
            }
        }"
            };

            
            
                var content = new StringContent(JsonSerializer.Serialize(graphqlQuery), Encoding.UTF8, "application/json");

                var response = await httpClient.PostAsync("http://localhost:4000/graphql", content);

                if (!response.IsSuccessStatusCode)
                {
                    lblNombreControl.Text = " Error al consultar SEDUCA.";
                    return; 
                }

                var responseData = await response.Content.ReadAsStringAsync();
                using var jsonDoc = JsonDocument.Parse(responseData);

                var root = jsonDoc.RootElement;

            if (root.GetProperty("data").TryGetProperty("persona", out var personaData))
            {
                if (personaData.ValueKind == JsonValueKind.Null)
                {
                    lblNombreControl.Text = "Persona es null según SEDUCA.";
                    return;
                }

                if (personaData.TryGetProperty("esBachiller", out var esBachillerProp) && esBachillerProp.ValueKind == JsonValueKind.True)
                {
                    // esBachiller es true, continuar con el flujo
                }
                else
                {
                    lblNombreControl.Text = " No es bachiller según SEDUCA.";
                    return;
                }
            }
            else
            {
                lblNombreControl.Text = "No se encontró persona en SEDUCA.";
                return;
            }



            lblNombreControl.Text = " Es bachiller. Consultando ACADEMICO...";

            // REST – Verificar si el título ya fue registrado
            var academicoUrl = $"http://localhost:8000/api/titulos/{ci}";
            var checkResponse = await httpClient.GetAsync(academicoUrl);

            if (checkResponse.StatusCode == HttpStatusCode.OK)
            {
                // Leer y verificar si hay datos reales
                var contents = await checkResponse.Content.ReadAsStringAsync();
                if (!string.IsNullOrWhiteSpace(contents) && contents.Contains("ci"))
                {
                    lblNombreControl.Text = "Ya está registrado en ACADEMICO.";
                    return;
                }
            }
            else if (checkResponse.StatusCode != HttpStatusCode.NotFound)
            {
                // Otro error inesperado (500, 403, etc.)
                lblNombreControl.Text = "Error al verificar en ACADEMICO.";
                return;
            }


            // REST – Registrar el título
            var nuevoTitulo = new
            {
                ci = ci,
                nombres =  nombres,
                primer_apellido = primerApellido,
                segundo_apellido = segundoApellido,
                titulo = titulo,    
                fecha_emision = DateTime.Now.ToString("yyyy-MM-dd")
            };

            var postContent = new StringContent(JsonSerializer.Serialize(nuevoTitulo), Encoding.UTF8, "application/json");

            var postResponse = await httpClient.PostAsync("http://localhost:8000/api/titulos", postContent);

            if (postResponse.IsSuccessStatusCode)
            {
                lblNombreControl.Text = "Título registrado en ACADEMICO.";
            }
            else
            {
                lblNombreControl.Text = "Error al registrar el título.";
            }
        }

    }
}
