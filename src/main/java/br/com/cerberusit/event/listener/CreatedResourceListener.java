package br.com.cerberusit.event.listener;

import br.com.cerberusit.event.CreatedResourceEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletResponse;
import java.net.URI;

public class CreatedResourceListener implements ApplicationListener<CreatedResourceEvent> {

    @Override
    public void onApplicationEvent(CreatedResourceEvent event) {
        HttpServletResponse response = event.getResponse();
        Long id = event.getId();
        addLocationHeader(response, id);
    }

    private void addLocationHeader(HttpServletResponse response, Long id) {
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequestUri().path("/{id}")
                .buildAndExpand(id)
                .toUri();
        response.setHeader("Location", uri.toASCIIString());
    }
}
