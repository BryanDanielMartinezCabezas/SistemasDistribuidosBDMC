<?php

namespace App\Http\Controllers;

use App\Models\Factura;
use Illuminate\Http\Request;

class FacturaController extends Controller
{
    // GET /facturas/{ci}
public function getPorCI($ci)       
{
    $facturas = Factura::where('ci', $ci)
                        ->where('estado', 'Pendiente')
                        ->get();

    if ($facturas->isEmpty()) {
        return response()->json([
            'mensaje' => 'No se encontraron facturas pendientes para el CI proporcionado.'
        ], 404);
    }

    return response()->json($facturas);
}





public function pagarFactura($id)
{
    $factura = Factura::find($id);

    if (!$factura) {
        return response()->json(['mensaje' => 'Factura no encontrada.'], 404);
    }

    if ($factura->estado === 'Pagado') {
        return response()->json(['mensaje' => 'La factura ya está pagada.'], 400);
    }

    $factura->estado = 'Pagado';
    $factura->save();

    return response()->json([
        'mensaje' => 'Factura pagada correctamente.',
        'factura' => $factura
    ]);
}
}
