/*
 * Copyright (C) 2003-2014 eXo Platform SAS.
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 3 of
 * the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this software; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see the FSF site: http://www.fsf.org.
 */


@Application(defaultController = CategoriesManagementController.class)
@Portlet

/**
@Scripts(
        {
                //@Script(value = "javascript/jquery-3.2.1.min.js", id = "jquery",location = AssetLocation.SERVER),
                //@Script(value = "javascript/vue.esm.browser.js", id = "vuejs",location = AssetLocation.SERVER, header = true),
                //@Script(value = "javascript/categories.js", id = "categoriesjs",location = AssetLocation.SERVER,depends = "vuejs")

        }
)
*/

@Stylesheets(
        {
                @Stylesheet(value = "/org/exoplatform/addons/categories/portlets/management/assets/global.css", location = AssetLocation.APPLICATION, id = "global")
        }

)
@Bindings(
        {
                @Binding(value = org.exoplatform.services.organization.OrganizationService.class),
                @Binding(value = org.exoplatform.categories.service.CategoryService.class)

        }
)

@Less(value = {"global.less"}, minify = true)
@Assets("*")
package org.exoplatform.addons.categories.portlets.management;


import juzu.Application;
import juzu.asset.AssetLocation;
import juzu.plugin.asset.*;
import juzu.plugin.binding.Binding;
import juzu.plugin.binding.Bindings;
import juzu.plugin.less.Less;
import juzu.plugin.portlet.Portlet;