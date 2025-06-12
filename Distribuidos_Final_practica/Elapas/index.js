const express = require('express');
const { Sequelize, DataTypes } = require('sequelize');

const app = express();
app.use(express.json());
app.use(express.urlencoded({ extended: true }));


//para crear la base de datos aca usamos los siguientes comandosnpm init -y
//npm install express

//crear el archivo index.js o server.js tu archivo main
//para ejecutarlo node index.js
//Para la base de  datos npm install sequelize mysql2 e incluir el modelo de la tabla

//Conexión con MySQL
const sequelize = new Sequelize('elapas2', 'root', '', {
  host: 'localhost',
  dialect: 'mysql',
  logging: false
});

//Modelo de Factura
const Factura = sequelize.define('Factura', {
  ci: {
    type: DataTypes.STRING,
    allowNull: false
  },
  monto: {
    type: DataTypes.FLOAT,
    allowNull: false
  },
  estado: {
    type: DataTypes.STRING,
    defaultValue: 'Pendiente'
  }
}, {
  tableName: 'facturas',   // usa exactamente la tabla 'facturas'
  freezeTableName: true,    // no pluralice ni modifique el nombre
  timestamps: false         // no espere createdAt/updatedAt
});

// Sincroniza base de datos (crea tabla si no existe)
sequelize.sync().then(() => {
  console.log('Base de datos sincronizada');
});

// GET facturas pendientes por CI
app.get('/facturas/:ci', async (req, res) => {
  try {
    const facturas = await Factura.findAll({
      where: { ci: req.params.ci, estado: 'Pendiente' }
    });

    if (facturas.length === 0) {
      return res.status(404).json({ mensaje: 'No se encontraron facturas pendientes para este CI' });
    }

    res.json(facturas);
  } catch (error) {
    console.error('ERROR al consultar facturas:', error);
    res.status(500).json({ error: 'Error al consultar las facturas' });
  }
});


//  PUT para pagar factura solo con el ID
app.put('/facturas/pagar/:id', async (req, res) => {
  try {
    const factura = await Factura.findByPk(req.params.id);

    if (!factura) {
      return res.status(404).json({ mensaje: 'Factura no encontrada' });
    }

    if (factura.estado === 'Pagado') {
      return res.status(400).json({ mensaje: 'La factura ya está pagada' });
    }

    factura.estado = 'Pagado';
    await factura.save();

    res.json({ mensaje: 'Factura pagada con éxito', factura });
  } catch (error) {
    res.status(500).json({ error: 'Error al pagar la factura' });
  }
});



// Iniciar servidor
const PORT = 3000;
app.listen(PORT, () => console.log(`Servidor corriendo en puerto ${PORT}`));
