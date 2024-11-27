package seg3x02.employeeGql.entity

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "employee")
data class Employee(
    @Id var employeeId: String = "",
    var name: String,
    var dateOfBirth: String? = null,
    var city: String? = null,
    var salary: Float? = null,
    var gender: String? = null,
    var email: String? = null
)
