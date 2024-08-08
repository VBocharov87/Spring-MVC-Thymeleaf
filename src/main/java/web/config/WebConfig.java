package web.config;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;

// конфигурационный класс Spring Web MVC

@Configuration
@EnableWebMvc           // заменяет <mvc:annotation-driven/> в applicationContext.xml
@ComponentScan("web")

//WebMvcConfigurer — это интерфейс в Spring, который предоставляет методы для настройки и конфигурации Spring MVC.
// Он используется для настройки различных аспектов веб-приложения, таких как маршрутизация, обработка исключений,
// форматирование данных, и многое другое.
//
//Основные возможности WebMvcConfigurer:
//Конфигурация ресурса:
//          Настройка статических ресурсов, таких как CSS, JavaScript, изображения и т.д.
//
//Настройка представлений (View):
//          Конфигурация представлений и механизмов их разрешения (например, настройка Thymeleaf, JSP, и т.д.).

//Настройка контроллеров:
//          Добавление или замена контроллеров, настройка маппинга URL.

//Настройка форматирования данных:
//          Настройка форматировщиков и конвертеров для преобразования данных между представлением и моделью.

//Настройка обработчиков исключений:
//          Конфигурация обработчиков исключений для обработки ошибок в приложении

public class WebConfig implements WebMvcConfigurer {

//    ApplicationContext - представляет собой Spring Container.
//    Для получения бина из Spring Container нам нужно создать ApplicationContext

    private final ApplicationContext applicationContext;

    public WebConfig(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

//    SpringResourceTemplateResolver, который является частью системы шаблонов Thymeleaf. Он используется для
//    разрешения и нахождения шаблонов (например, HTML-файлов) в вашем веб-приложении.
//    Это специальный класс Thymeleaf, который отвечает за нахождение и загрузку шаблонов из файловой системы
//    или других ресурсов.

    @Bean
    public SpringResourceTemplateResolver templateResolver() {
        SpringResourceTemplateResolver templateResolver = new SpringResourceTemplateResolver();

//        Устанавливает контекст приложения, что позволяет шаблонам быть в курсе контекста и использовать его
        templateResolver.setApplicationContext(applicationContext);

//        Путь хранения шаблонов
        templateResolver.setPrefix("/WEB-INF/pages/");
        templateResolver.setSuffix(".html");
        return templateResolver;
    }

//    SpringTemplateEngine является центральным компонентом Thymeleaf для обработки шаблонов и
//    рендеринга HTML-страниц

    @Bean
    public SpringTemplateEngine templateEngine() {
        SpringTemplateEngine templateEngine = new SpringTemplateEngine();

//      скармливаем ему templateResolver, который отвечает за место хранения шаблонов и их тип
        templateEngine.setTemplateResolver(templateResolver());

//      компилятор Spring Expression Language (SpringEL), может улучшить производительность за счет
//      предварительной компиляции выражений, используемых в шаблонах.
        templateEngine.setEnableSpringELCompiler(true);
        return templateEngine;
    }


//    этот метод используется для настройки ViewResolver в Spring MVC с помощью Thymeleaf. В данном случае он
//    добавляет и настраивает Thymeleaf как основной механизм рендеринга представлений (views) в вашем приложении.
//    Если не использовать его, то по-умолчанию будет использоваться InternalResourceViewResolver — это класс,
//    который помогает находить и возвращать представления, такие как JSP страницы
    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        ThymeleafViewResolver resolver = new ThymeleafViewResolver();
        resolver.setTemplateEngine(templateEngine());

//     Spring MVC будет использовать данный (ThymeleafViewResolver) resolver для поиска и рендеринга представлений
        registry.viewResolver(resolver);
    }
}