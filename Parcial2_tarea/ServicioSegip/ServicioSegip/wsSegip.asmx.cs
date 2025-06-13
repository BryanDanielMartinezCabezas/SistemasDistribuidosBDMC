using System;
using System.Collections.Generic;
using System.Data;
using System.Data.SqlClient;
using System.Linq;
using System.Web;
using System.Web.Services;
using System.Configuration;
using MySql.Data.MySqlClient;


namespace ServicioSegip
{
    /// <summary>
    /// Descripción breve de wsSegip
    /// </summary>
    [WebService(Namespace = "http://tempuri.org/")]
    [WebServiceBinding(ConformsTo = WsiProfiles.BasicProfile1_1)]
    [System.ComponentModel.ToolboxItem(false)]





    public class wsSegip : System.Web.Services.WebService
    {
        private string conexion = ConfigurationManager.ConnectionStrings["MySqlConnection"].ConnectionString;

        [WebMethod]
        public bool VerificarDatos(string CI)
        {
            using (var con = new MySqlConnection(conexion))
            {
                con.Open();
                var query = "SELECT COUNT(1) FROM Personas WHERE CI = @CI";
                var cmd = new MySqlCommand(query, con);
                cmd.Parameters.AddWithValue("@CI", CI);
                var count = Convert.ToInt32(cmd.ExecuteScalar());
                return count > 0;
            }
        }

        [WebMethod]
        public Persona ObtenerDatos(string CI)
        {
            using (var con = new MySqlConnection(conexion))
            {
                con.Open();
                var query = "SELECT CI, Nombres, PrimerApellido, SegundoApellido FROM Personas WHERE CI = @CI";
                var cmd = new MySqlCommand(query, con);
                cmd.Parameters.AddWithValue("@CI", CI);
                using (var reader = cmd.ExecuteReader())
                {
                    if (reader.Read())
                    {
                        return new Persona
                        {
                            CI = reader["CI"].ToString(),
                            Nombres = reader["Nombres"].ToString(),
                            PrimerApellido = reader["PrimerApellido"].ToString(),
                            SegundoApellido = reader["SegundoApellido"].ToString()
                        };
                    }
                    return null;
                }
            }
        }

    }


    // Clase para devolver datos de persona
    public class Persona
    {
        public string CI { get; set; }
        public string Nombres { get; set; }
        public string PrimerApellido { get; set; }
        public string SegundoApellido { get; set; }
    }
}
