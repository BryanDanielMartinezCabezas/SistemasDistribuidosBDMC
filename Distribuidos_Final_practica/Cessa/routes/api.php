<?php

use Illuminate\Http\Request;
use Illuminate\Support\Facades\Route;
use App\Http\Controllers\FacturaController;

Route::get('/facturas/{ci}', [FacturaController::class, 'getPorCI']);
Route::put('/facturas/{id}', [FacturaController::class, 'pagarFactura']);
