using System;
using System.Windows.Forms;

namespace LabSoapForm
{
    public partial class Form1 : Form
    {
        private rsCotizar.WebService1SoapClient servicio;

        public Form1()
        {
            InitializeComponent();
            Load += Form1_Load;
            FormClosing += Form1_FormClosing;
            button1.Click += button1_Click;
        }

        void Form1_Load(object sender, EventArgs e)
        {
            servicio = new rsCotizar.WebService1SoapClient(
                rsCotizar.WebService1SoapClient.EndpointConfiguration.WebService1Soap12
            );
        }

        void button1_Click(object sender, EventArgs e)
        {
            var fecha = textBox1.Text.Trim();
            if (string.IsNullOrEmpty(fecha))
            {
                label1.Text = "ingresa fecha";
                return;
            }
            try
            {
                label1.Text = servicio.obtenerCotizacion(fecha);
            }
            catch
            {
                label1.Text = "error";
            }
        }

        void Form1_FormClosing(object sender, FormClosingEventArgs e)
        {
            try { servicio.Close(); } catch { servicio.Abort(); }
        }

        private void button2_Click(object sender, EventArgs e)
        {

        }
    }
}
