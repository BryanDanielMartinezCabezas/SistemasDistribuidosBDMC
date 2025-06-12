const express = require('express');
const { graphqlHTTP } = require('express-graphql');
const {
  GraphQLSchema,
  GraphQLObjectType,
  GraphQLString,
  GraphQLFloat,
  GraphQLID,
  GraphQLList
} = require('graphql');

const mysql = require('mysql2/promise');
const pool = mysql.createPool({
  host: 'localhost',
  user: 'root',
  password: '',
  database: 'entel',
  waitForConnections: true,
  connectionLimit: 10,
  queueLimit: 0
});

const FacturaType = new GraphQLObjectType({
  name: 'Factura',
  fields: () => ({
    id: { type: GraphQLID },
    ci: { type: GraphQLString },
    monto: { type: GraphQLFloat },
    estado: { type: GraphQLString }
  })
});

const RootQuery = new GraphQLObjectType({
  name: 'RootQueryType',
  fields: {
    facturasPorCI: {
      type: new GraphQLList(FacturaType),
      args: { ci: { type: GraphQLString } },
      resolve: async (_, args) => {
        const [rows] = await pool.query('SELECT * FROM facturas WHERE ci = ? AND estado = "Pendiente"', [args.ci]);
        return rows;
      }
    }
  }
});

const Mutation = new GraphQLObjectType({
  name: 'Mutation',
  fields: {
    pagarFactura: {
      type: FacturaType,
      args: { id: { type: GraphQLID } },
      resolve: async (_, args) => {
        const [rows] = await pool.query('SELECT * FROM facturas WHERE id = ?', [args.id]);
        if (rows.length === 0) throw new Error('Factura no encontrada');
        const factura = rows[0];
        if (factura.estado === 'Pagado') throw new Error('Ya está pagada');

        await pool.query('UPDATE facturas SET estado = "Pagado" WHERE id = ?', [args.id]);

        factura.estado = 'Pagado';
        return factura;
      }
    }
  }
});

const schema = new GraphQLSchema({
  query: RootQuery,
  mutation: Mutation
});

const app = express();

app.use('/graphql', graphqlHTTP({
  schema,
  graphiql: true
}));

app.listen(4000, () => {
  console.log('Servidor GraphQL con MySQL corriendo en http://localhost:4000/graphql');
});
