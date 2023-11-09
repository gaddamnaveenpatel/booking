package com.example.BookMyShow.Config;


import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;

@OpenAPIDefinition(
        info = @Info(

                contact = @Contact(

                        name = "gaddam",

                        email = "gnaveenpatel.com",

                        url = "http://bookmyshow.com"

                ),

                description = "openapi doucumention for sprinboot for secuirty",

                title = "openapi specftiction - gaddam ",

                version = "1,0",
                license = @License(

                        name = "Licence name"

                )
        )
)

public class OpenApiConfig {



}
