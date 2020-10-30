package com.lnsantos.stefaniniandroidtest.core.repositories.interfaces

interface RemoteRepository<T,R> {

    suspend fun fetch(searchKey : T) : R

}