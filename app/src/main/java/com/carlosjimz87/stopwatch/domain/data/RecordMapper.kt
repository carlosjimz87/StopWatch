package com.carlosjimz87.stopwatch.domain.data

import com.carlosjimz87.stopwatch.data.models.RecordResponse
import com.carlosjimz87.stopwatch.domain.models.Record

object RecordMapper: EntityMapper<RecordResponse,Record> {
    override fun mapFromResponse(entity: RecordResponse): Record {
        return Record(
            entity.datetime,
            entity.time
        )
    }

    override fun mapToResponse(domainModel: Record): RecordResponse {
        return RecordResponse(
            domainModel.datetime,
            domainModel.time
        )
    }

}