package com.springboot.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class DemoApplication {

	@RequestMapping("/")
	String index() {
		return "hello Devtools,hh!";
	}

	/**
	 * 热部署
	 * （1）devtools可以实现页面热部署（即页面修改后会立即生效，这个可以直接在application.properties文件中配置spring.thymeleaf.cache=false来实现），
	 *     实现类文件热部署（类文件修改后不会立即生效），实现对属性文件的热部署。
	 *     即devtools会监听classpath下的文件变动，并且会立即重启应用（发生在保存时机），注意：因为其采用的虚拟机机制，该项重启是很快的
	 * （2) <fork>true</fork>（意思在maven 写了） 配置了true后在修改java文件后也就支持了热启动，
	 *      不过这种方式是属于项目重启（速度比较快的项目重启），会清空session中的值，也就是如果有用户登陆的话，项目重启后需要重新登陆。
	 *
	 *    默认情况下，/META-INF/maven，/META-INF/resources，/resources，/static，/templates，/public这些文件夹下的文件修改不会使应用重启，
	 *    但是会重新加载（devtools内嵌了一个LiveReload server，当资源发生改变时，浏览器刷新。
	 *
	 *  当我们修改了类文件后，idea不会自动编译，得修改idea设置。
	 *
	 * （1）File-Settings-Compiler-Build Project automatically
	 * （2）ctrl + shift + alt + / ,选择Registry,勾上 Compiler autoMake allow when app running
	 *
	 * 热部署原理
	 * 热部署就是在运行时更新Java类文件。
	 * 通过切换不同的类加载器来实现的。
	 *
	 *  深层原理是使用了两个ClassLoader，一个Classloader加载那些不会改变的类（第三方Jar包），另一个ClassLoader加载会更改的类，
	 *  称为restart ClassLoader,这样在有代码更改的时候，
	 *  原来的restart ClassLoader 被丢弃，重新创建一个restart ClassLoader，由于需要加载的类相比较少，所以实现了较快的重启时间。
	 *
	 *
	 * 想要实现热部署可以分以下三个步骤：
	 * 1、销毁该自定义ClassLoader
	 * 2、更新class类文件
	 * 3、创建新的ClassLoader去加载更新后的class类文件。
	 *
	 * OSGI（面向Java的动态模型系统）的最关键理念就是应用模块（bundle）化，对于每一个bundle,都有其自己的类加载器，
	 * 当需要更新bundle时，把bundle和它的类加载器一起替换掉，就可以实现模块的热替换。
	 *
	 * @param args
	 */
	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
}
