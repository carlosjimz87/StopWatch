package com.carlosjimz87.stopwatch.domain.data

interface EntityMapper <Entity,DomainModel>{
    fun mapFromResponse(entity:Entity): DomainModel
    fun mapToResponse(domainModel: DomainModel): Entity
}