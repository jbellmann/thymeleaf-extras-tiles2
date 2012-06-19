/*
 * =============================================================================
 * 
 *   Copyright (c) 2011-2012, The THYMELEAF team (http://www.thymeleaf.org)
 * 
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 * 
 *       http://www.apache.org/licenses/LICENSE-2.0
 * 
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 * 
 * =============================================================================
 */
package org.thymeleaf.tiles2.spring.web.renderer;

import java.util.Locale;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.tiles.context.TilesRequestContext;
import org.springframework.context.ApplicationContext;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.IContext;
import org.thymeleaf.spring3.context.SpringWebContext;
import org.thymeleaf.tiles2.localeresolver.LocaleResolverHolder;
import org.thymeleaf.tiles2.web.renderer.AbstractThymeleafWebAttributeRenderer;



/**
 * 
 * @author Daniel Fern&aacute;ndez
 * 
 * @since 2.0.9
 *
 */
public class SpringThymeleafAttributeRenderer 
        extends AbstractThymeleafWebAttributeRenderer {

    
    private final ApplicationContext springApplicationContext;
    private final TemplateEngine templateEngine;
    
    
    public SpringThymeleafAttributeRenderer(
            final LocaleResolverHolder localeResolverHolder, final ApplicationContext applicationContext, 
            final TemplateEngine templateEngine) {
        
        super(localeResolverHolder);
        
        this.springApplicationContext = applicationContext;
        this.templateEngine = templateEngine;
        
    }

    
    

    @Override
    protected TemplateEngine getTemplateEngine(final String templateName,
            final TilesRequestContext request, final HttpServletRequest httpServletRequest,
            final ServletContext servletContext) {
        return this.templateEngine;
    }




    @Override
    @SuppressWarnings("cast")
    protected IContext getContext(final String templateName,
            final TilesRequestContext request, final HttpServletRequest httpServletRequest,
            final ServletContext servletContext, final Locale locale,
            final Map<String, Object> variables) {

        return new SpringWebContext(
                httpServletRequest, servletContext, locale, (Map<String,?>)variables,
                this.springApplicationContext);
        
    }

    
}