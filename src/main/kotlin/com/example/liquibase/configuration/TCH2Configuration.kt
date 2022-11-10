//package com.example.liquibase.configuration
//
//import org.h2.tools.Server
//import org.springframework.context.annotation.Bean
//import org.springframework.context.annotation.Configuration
//import org.springframework.context.annotation.Profile
//import java.sql.SQLException
//
///**
// * H2 DB will only be used when running test in local
// * H2 URL: jdbc:h2:tcp://localhost:9090/mem:test
// */
//@Profile("local")
//@Configuration
//internal class TCH2Configuration {
//    @Bean(initMethod = "start", destroyMethod = "stop")
//    @Throws(SQLException::class)
//    fun h2Server(): Server = Server.createTcpServer("-tcp", "-tcpAllowOthers", "-tcpPort", "9090")
//}
