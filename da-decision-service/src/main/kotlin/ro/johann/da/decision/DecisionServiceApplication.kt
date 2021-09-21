package ro.johann.da.decision

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.retry.annotation.EnableRetry

@EnableRetry
@SpringBootApplication
class DecisionServiceApplication

fun main(args: Array<String>) {
	runApplication<DecisionServiceApplication>(*args)
}
