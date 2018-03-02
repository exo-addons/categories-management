package org.exoplatform.addons.categories.portlets.management;

import juzu.*;
import juzu.request.SecurityContext;
import juzu.template.Template;
import org.exoplatform.categories.entities.domain.CategoryEntity;
import org.exoplatform.categories.service.CategoryService;
import org.exoplatform.commons.juzu.ajax.Ajax;
import org.exoplatform.web.application.RequestContext;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import javax.inject.Inject;
import javax.portlet.PortletPreferences;
import java.util.ArrayList;
import java.util.List;

public class CategoriesManagementController {

    @Inject
    @Path("index.gtmpl")
    Template indexTemplate;

    @Inject
    PortletPreferences portletPreferences;

    @Inject
    CategoryService categoryService;

    private String context = "";

    @View
    public Response.Content index() {
        //--- Get context pref each time we refresh the page
        //TODO should be used once
        context = portletPreferences.getValue("context", "none");
        String remoteUser = RequestContext.getCurrentInstance().getRemoteUser();

        return indexTemplate.with()
                .set("country", "country")
                .set("code", "code")
                .ok();
    }

    @Resource(method = HttpMethod.POST)
    @Ajax
    public Response init(SecurityContext securityContext) throws JSONException {

        //--- Get context pref each time we refresh the page
        //TODO should be used once
        context = portletPreferences.getValue("context", "none");

        String currentUser = securityContext.getRemoteUser();
        if (currentUser == null) {
            return Response.status(401).body("You must login to create new project");
        }

        //----Load categories by context
        List<CategoryEntity> categories = null; //categoryService.findcategoriesByContext(context);
        categories = new ArrayList<CategoryEntity>();
        //--- Fake categories
        CategoryEntity ce = new CategoryEntity();
        ce.setId(1L);
        ce.setTitle("categories a");
        ce.setDescription("category description A");
        ce.setIcon("categorya.png");
        categories.add(ce);
        ce = new CategoryEntity();
        ce.setId(2L);
        ce.setTitle("categories b");
        ce.setDescription("category description B");
        ce.setIcon("categoryb.png");
        categories.add(ce);
        JSONArray categoriesJson = new JSONArray();
        //--- Build json response
        categories.forEach(elt -> {
            JSONObject json = new JSONObject(elt);
            categoriesJson.put(json);

        });

        return Response.ok(categoriesJson.toString()).withMimeType("application/json; charset=UTF-8").withHeader("Cache-Control", "no-cache");
    }

    @Resource(method = HttpMethod.POST)
    @Ajax
    public Response delete(String title, SecurityContext securityContext) throws JSONException {

        //--- Get context pref each time we refresh the page
        //TODO should be used once
        context = portletPreferences.getValue("context", "none");
        //--- Get category by title
        CategoryEntity cEntity= categoryService.findCategoryByTitle(title);
        //--- Delete category
        categoryService.deleteCategory(cEntity);

        return Response.ok("{\"status\":\"ok\"}").withMimeType("application/json; charset=UTF-8").withHeader("Cache-Control", "no-cache");
    }

    @Resource(method = HttpMethod.POST)
    @Ajax
    public Response add(String title, String description, String icon, SecurityContext securityContext) throws JSONException {

        //--- Get context pref each time we refresh the page
        //TODO should be used once
        context = portletPreferences.getValue("context", "none");
        //--- build a category
        CategoryEntity cEntity = new CategoryEntity();
        cEntity.setTitle(title);
        cEntity.setDescription(description);
        cEntity.setIcon(icon);

        //--- Delete category
        CategoryEntity cEntityPersisted = categoryService.createCategory(cEntity);
        JSONObject json = new JSONObject(cEntityPersisted);

        return Response.ok(json.toString()).withMimeType("application/json; charset=UTF-8").withHeader("Cache-Control", "no-cache");
    }

}
