
package com.example.infraestructure.http.children;


import com.example.aplication.children.ChildrenQuery;
import com.example.aplication.children.SelectChildrenQueryHandle;
import com.example.domain.children.Children;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@EnableAutoConfiguration
@CrossOrigin(origins = "http://localhost:9080")
@RequestMapping("/children")
public class SelectChildrenController {

    @Autowired
    private SelectChildrenQueryHandle commandBus;


    @RequestMapping("/select")
    public Children selectAction(@RequestParam(defaultValue = "key") String key) {

        final ChildrenQuery query = new ChildrenQuery();
        query.setId(key);

        return this.commandBus.handle(query);
    }

}
