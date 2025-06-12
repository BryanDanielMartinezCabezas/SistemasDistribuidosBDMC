using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Services;

namespace PlataformaPagos
{
    [Serializable]
    public class Factura
    {
        public int Id { get; set; }
        public string Ci { get; set; }
        public double Monto { get; set; }
        public string Estado { get; set; }
    }
    /// <summary>
    /// Descripción breve de wsPagos
    /// </summary>
    [WebService(Namespace = "http://tempuri.org/")]
    [WebServiceBinding(ConformsTo = WsiProfiles.BasicProfile1_1)]
    [System.ComponentModel.ToolboxItem(false)]
    // Para permitir que se llame a este servicio web desde un script, usando ASP.NET AJAX, quite la marca de comentario de la línea siguiente. 
    // [System.Web.Script.Services.ScriptService]
    public class wsPagos : System.Web.Services.WebService
    {
        private static List<Factura> facturasDB = new List<Factura>
        {
            new Factura { Id = 1, Ci = "1234567", Monto = 100.50, Estado = "Pendiente" },
            new Factura { Id = 2, Ci = "1234567", Monto = 200.75, Estado = "Pendiente" },
            new Factura { Id = 3, Ci = "7654321", Monto = 300.00, Estado = "Pagado" }
        };

        [WebMethod]
        public Factura[] ConsultarDeudas(string ci)
        {
            // Devuelve las facturas con estado Pendiente para el CI dado
            return facturasDB.Where(f => f.Ci == ci && f.Estado == "Pendiente").ToArray();
        }


        [WebMethod]
        public void Pagar(Factura[] facturas)
        {
            foreach (var factura in facturas)
            {

                var f = facturasDB.FirstOrDefault(x => x.Id == factura.Id);
                if (f != null && f.Estado == "Pendiente")
                {
                    f.Estado = "Pagado";
                }
            }
        }

    }
}
