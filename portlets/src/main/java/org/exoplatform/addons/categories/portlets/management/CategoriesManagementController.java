package org.exoplatform.addons.categories.portlets.management;

import juzu.Path;
import juzu.Response;
import juzu.SessionScoped;
import juzu.View;
import juzu.template.Template;

import javax.inject.Inject;
import javax.portlet.PortletPreferences;

import org.exoplatform.services.organization.OrganizationService;

import org.exoplatform.web.application.RequestContext;

@SessionScoped
public class CategoriesManagementController {

    @Inject
    @Path("index.gtmpl")
    Template indexTemplate;

    @Inject
    PortletPreferences portletPreferences;

    OrganizationService organizationService_;

    @View
    public Response.Content index()
    {
        String remoteUser = RequestContext.getCurrentInstance().getRemoteUser();
        System.out.println ("Juste a message !!!!!! ????");

        return indexTemplate.with()
                .set("country", "country")
                .set("code", "code")
                .ok();
    }

}
