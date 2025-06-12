namespace AplicacionPago
{
    partial class Form1
    {
        /// <summary>
        ///  Required designer variable.
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary>
        ///  Clean up any resources being used.
        /// </summary>
        /// <param name="disposing">true if managed resources should be disposed; otherwise, false.</param>
        protected override void Dispose(bool disposing)
        {
            if (disposing && (components != null))
            {
                components.Dispose();
            }
            base.Dispose(disposing);
        }

        #region Windows Form Designer generated code

        /// <summary>
        ///  Required method for Designer support - do not modify
        ///  the contents of this method with the code editor.
        /// </summary>
        private void InitializeComponent()
        {
            txtCI = new TextBox();
            label1 = new Label();
            btnBuscar = new Button();
            lblFacturas = new Label();
            txtPagar = new TextBox();
            btnPagar = new Button();
            txtEmpresa = new TextBox();
            SuspendLayout();
            // 
            // txtCI
            // 
            txtCI.Location = new Point(39, 51);
            txtCI.Name = "txtCI";
            txtCI.Size = new Size(100, 23);
            txtCI.TabIndex = 0;
            // 
            // label1
            // 
            label1.AutoSize = true;
            label1.Location = new Point(184, 51);
            label1.Name = "label1";
            label1.Size = new Size(18, 15);
            label1.TabIndex = 3;
            label1.Text = "CI";
            // 
            // btnBuscar
            // 
            btnBuscar.Location = new Point(39, 104);
            btnBuscar.Name = "btnBuscar";
            btnBuscar.Size = new Size(75, 23);
            btnBuscar.TabIndex = 4;
            btnBuscar.Text = "buscar";
            btnBuscar.UseVisualStyleBackColor = true;
            btnBuscar.Click += btnBuscar_Click;
            // 
            // lblFacturas
            // 
            lblFacturas.AutoSize = true;
            lblFacturas.Location = new Point(245, 54);
            lblFacturas.Name = "lblFacturas";
            lblFacturas.Size = new Size(51, 15);
            lblFacturas.TabIndex = 5;
            lblFacturas.Text = "Facturas";
            // 
            // txtPagar
            // 
            txtPagar.Location = new Point(39, 253);
            txtPagar.Name = "txtPagar";
            txtPagar.Size = new Size(100, 23);
            txtPagar.TabIndex = 6;
            // 
            // btnPagar
            // 
            btnPagar.Location = new Point(39, 298);
            btnPagar.Name = "btnPagar";
            btnPagar.Size = new Size(75, 23);
            btnPagar.TabIndex = 7;
            btnPagar.Text = "Pagar";
            btnPagar.UseVisualStyleBackColor = true;
            btnPagar.Click += btnPagar_Click;
            // 
            // txtEmpresa
            // 
            txtEmpresa.Location = new Point(39, 206);
            txtEmpresa.Name = "txtEmpresa";
            txtEmpresa.Size = new Size(100, 23);
            txtEmpresa.TabIndex = 8;
            // 
            // Form1
            // 
            AutoScaleDimensions = new SizeF(7F, 15F);
            AutoScaleMode = AutoScaleMode.Font;
            ClientSize = new Size(800, 450);
            Controls.Add(txtEmpresa);
            Controls.Add(btnPagar);
            Controls.Add(txtPagar);
            Controls.Add(lblFacturas);
            Controls.Add(btnBuscar);
            Controls.Add(label1);
            Controls.Add(txtCI);
            Name = "Form1";
            Text = "Form1";
            ResumeLayout(false);
            PerformLayout();
        }

        #endregion

        private TextBox txtCI;
        private Label label1;
        private Button btnBuscar;
        private Label lblFacturas;
        private TextBox txtPagar;
        private Button btnPagar;
        private TextBox txtEmpresa;
    }
}
