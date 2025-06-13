<?php

use Illuminate\Http\Request;
use Illuminate\Support\Facades\Route;
use App\Http\Controllers\TituloController;

Route::get('/user', function (Request $request) {
    return $request->user();
})->middleware('auth:sanctum');
Route::apiResource('titulos', TituloController::class);
Route::get('/titulos/{ci}', [TituloController::class, 'show']);
