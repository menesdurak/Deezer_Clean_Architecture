package com.menesdurak.appcentcasestudycleanarchitecture.common.mapper

interface Mapper<I,O>{
    fun map(input:I):O
}