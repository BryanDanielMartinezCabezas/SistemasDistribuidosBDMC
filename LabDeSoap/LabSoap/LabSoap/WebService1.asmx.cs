using System;
using System.Configuration;
using System.Web.Services;
using MySql.Data.MySqlClient;

namespace LabSoap
{
    [WebService(Namespace = "http://tempuri.org/")]
    [WebServiceBinding(ConformsTo = WsiProfiles.BasicProfile1_1)]
    [System.ComponentModel.ToolboxItem(false)]
    public class WebService1 : System.Web.Services.WebService
    {
        // Leer la cadena de conexión desde web.config
        string connectionString = ConfigurationManager.ConnectionStrings["MySqlConnection"].ConnectionString;

        [WebMethod]
        public string HelloWorld()
        {
            return "Hola a todos";
        }

        [WebMethod]
        public string obtenerCotizacion(string Fecha)
        {
            try
            {
                using (MySqlConnection conn = new MySqlConnection(connectionString))
                {
                    conn.Open();
                    string query = "SELECT cotizacion, cotizacion_oficial FROM cotizaciones WHERE fecha = @fecha";
                    using (MySqlCommand cmd = new MySqlCommand(query, conn))
                    {
                        cmd.Parameters.AddWithValue("@fecha", Fecha);
                        using (MySqlDataReader reader = cmd.ExecuteReader())
                        {
                            if (reader.Read())
                            {
                                decimal cotizacion = reader.IsDBNull(0) ? 0 : reader.GetDecimal(0);
                                decimal cotizacionOficial = reader.IsDBNull(1) ? 0 : reader.GetDecimal(1);
                                return $"Cotización: {cotizacion}, Cotización Oficial: {cotizacionOficial}";
                            }
                            else
                            {
                                return "No se encontró la cotización para esa fecha.";
                            }
                        }
                    }
                }
            }
            catch (Exception ex)
            {
                return "Error al obtener cotización: " + ex.Message;
            }
        }

        [WebMethod]
        public string registrarCotizacion(string Fecha, double monto)
        {
            try
            {
                using (MySqlConnection conn = new MySqlConnection(connectionString))
                {
                    conn.Open();

                    string query = "INSERT INTO cotizaciones (fecha, cotizacion) VALUES (@fecha, @cotizacion)";
                    using (MySqlCommand cmd = new MySqlCommand(query, conn))
                    {
                        cmd.Parameters.AddWithValue("@fecha", Fecha);
                        cmd.Parameters.AddWithValue("@cotizacion", monto);
                        int result = cmd.ExecuteNonQuery();

                        if (result > 0)
                        {
                            return "Cotización registrada correctamente.";
                        }
                        else
                        {
                            return "No se pudo registrar la cotización.";
                        }
                    }
                }
            }
            catch (Exception ex)
            {
                return "Error al registrar cotización: " + ex.Message;
            }
        }
    }
}
