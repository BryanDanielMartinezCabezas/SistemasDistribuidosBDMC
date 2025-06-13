<?php

namespace App\Http\Controllers;

use App\Models\Titulo;
use Illuminate\Http\Request;

class TituloController extends Controller
{
    /**
     * GET /api/titulos
     * Display a listing of the resource.
     */
    public function index()
    {
        return response()->json(Titulo::all(), 200);
    }

    /**
     * POST /api/titulos
     * Store a newly created resource in storage.
     */
    public function store(Request $request)
    {
        $request->validate([
            'ci' => 'required|string',
            'nombres' => 'required|string',
            'primer_apellido' => 'required|string',
            'segundo_apellido' => 'nullable|string',
            'titulo' => 'required|string',
            'fecha_emision' => 'required|string',
        ]);

        $titulo = Titulo::create($request->all());

        return response()->json($titulo, 201);
    }

    /**
     * GET /api/titulos/{id}
     * Display the specified resource.
     */
public function show($ci)
{
    $titulo = Titulo::where('ci', $ci)->first();

    if (!$titulo) {
        return response()->json(['message' => 'Título no encontrado'], 404);
    }

    return response()->json($titulo, 200);
}


    /**
     * PUT /api/titulos/{id}
     * Update the specified resource in storage.
     */
    public function update(Request $request, Titulo $titulo)
    {
        $request->validate([
            'ci' => 'required|string',
            'nombres' => 'required|string',
            'primer_apellido' => 'required|string',
            'segundo_apellido' => 'nullable|string',
            'titulo' => 'required|string',
            'fecha_emision' => 'required|string',
        ]);

        $titulo->update($request->all());

        return response()->json($titulo, 200);
    }

    /**
     * DELETE /api/titulos/{id}
     * Remove the specified resource from storage.
     */
    public function destroy(Titulo $titulo)
    {
        $titulo->delete();

        return response()->json(null, 204);
    }
}
