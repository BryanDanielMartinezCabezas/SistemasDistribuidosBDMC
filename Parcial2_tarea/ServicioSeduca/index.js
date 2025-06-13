const express = require('express');
const { graphqlHTTP } = require('express-graphql');
const {
  GraphQLSchema,
  GraphQLObjectType,
  GraphQLString,
  GraphQLNonNull,
  GraphQLBoolean
} = require('graphql');
const mysql = require('mysql2/promise');

const app = express();

// 🔌 CONEXIÓN A BD REAL
const pool = mysql.createPool({
  host: 'localhost',
  user: 'root', // Cambia según tu usuario
  password: '', // Cambia según tu contraseña
  database: 'seduca'
});

// 🔎 DEFINICIÓN DE TIPO GraphQL
const PersonaType = new GraphQLObjectType({
  name: 'Persona',
  fields: () => ({
    CI: { type: GraphQLNonNull(GraphQLString) },
    Nombres: { type: GraphQLNonNull(GraphQLString) },
    PrimerApellido: { type: GraphQLNonNull(GraphQLString) },
    SegundoApellido: { type: GraphQLString },
    esBachiller: { type: GraphQLNonNull(GraphQLBoolean) }
  })
});

// 🔍 CONSULTA: Buscar persona por CI
const RootQueryType = new GraphQLObjectType({
  name: 'Query',
  fields: () => ({
    persona: {
      type: PersonaType,
      args: {
        CI: { type: GraphQLNonNull(GraphQLString) }
      },
      resolve: async (parent, args) => {
        const [rows] = await pool.execute('SELECT * FROM personas WHERE CI = ?', [args.CI]);
        if (rows.length > 0) {
          return rows[0];
        }
        return null;
      }
    }
  })
});

// ESQUEMA
const schema = new GraphQLSchema({
  query: RootQueryType
});

// SERVIDOR
app.use('/graphql', graphqlHTTP({
  schema: schema,
  graphiql: true
}));

const PORT = 4000;
app.listen(PORT, () => {
  console.log(`GraphQL corriendo en http://localhost:${PORT}/graphql`);
});
