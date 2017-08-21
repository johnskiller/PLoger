package com.skykiller.getsql;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GetsqlApplication implements CommandLineRunner {
	@Autowired
	private UserService userService;

	@Override
	public void run(String... args) {
		//System.out.println(this.helloWorldService.getHelloMessage());
		System.out.println("hello, world");
		System.out.println(this.userService.getAllUsers());
		System.out.println("-------------------------");
		System.out.println(userService.findUserByName("john"));
//		if (args.length > 0 && args[0].equals("exitcode")) {
//			throw new ExitException();
//		}
	}
	public static void main(String[] args) {
		SpringApplication.run(GetsqlApplication.class, args);
	}
}
