package seg3x02.employeeGql.resolvers

import org.springframework.graphql.data.method.annotation.Argument
import org.springframework.graphql.data.method.annotation.MutationMapping
import org.springframework.graphql.data.method.annotation.QueryMapping
import org.springframework.stereotype.Controller
import seg3x02.employeeGql.entity.Employee
import seg3x02.employeeGql.repository.EmployeeRepository
import seg3x02.employeeGql.resolvers.types.CreateEmployeeInput

@Controller
class EmployeesResolver(private val employeeRepository: EmployeeRepository) {

    @QueryMapping
    fun employees(): List<Employee> = employeeRepository.findAll()

    @QueryMapping
    fun employeeById(@Argument employeeId: String): Employee? =
        employeeRepository.findById(employeeId).orElse(null)

    @MutationMapping
    fun createEmployee(@Argument input: CreateEmployeeInput): Employee {
        val employee = Employee(
            name = input.name!!,
            dateOfBirth = input.dateOfBirth,
            city = input.city,
            salary = input.salary,
            gender = input.gender,
            email = input.email
        )
        return employeeRepository.save(employee)
    }

    @MutationMapping
    fun updateEmployee(@Argument employeeId: String, @Argument input: CreateEmployeeInput): Employee? {
        val optionalEmployee = employeeRepository.findById(employeeId)
        if (optionalEmployee.isPresent) {
            val employee = optionalEmployee.get()
            employee.name = input.name ?: employee.name
            employee.dateOfBirth = input.dateOfBirth ?: employee.dateOfBirth
            employee.city = input.city ?: employee.city
            employee.salary = input.salary ?: employee.salary
            employee.gender = input.gender ?: employee.gender
            employee.email = input.email ?: employee.email
            return employeeRepository.save(employee)
        }
        return null
    }

    @MutationMapping
    fun deleteEmployee(@Argument employeeId: String): Boolean {
        if (employeeRepository.existsById(employeeId)) {
            employeeRepository.deleteById(employeeId)
            return true
        }
        return false
    }
}
