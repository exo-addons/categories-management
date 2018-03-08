(function ($) {
  var cat = new Vue({
    el: '#categories-management',
    data: {
      categories: [],
      title: "",
      description: "",
      icon: "",
      successMessage: "",
      errorMessage: "",
      showSuccessMessage: false,
      showErrorMessage: false,
      isNew: true,
      i18n: []
    },
    methods: {
      deleteCategory: function (index, category) {
        var self = this;
        var $githubDiv = $("#categories-management");
        var urlCall = $githubDiv.jzURL("CategoriesManagementController.delete");

        $.ajax({
          type: 'GET',
          url: urlCall,
          data: {
            "title": category.title
          }

        }).done(function () {
          self.successMessage = "Category has been removed successfully.";
          self.showSuccessMessage = true;
        }).fail(function () {
          self.errorMessage = "Error when dropping category. Please try again!";
          self.showErrorMessage = true
        }).always(function () {
          self.categories.splice(index, 1);
          self.showSuccessMessage = false;
          self.showErrorMessage = false;
        });

      },
      addCategory: function () {
        var self = this;
        var $githubDiv = $("#categories-management");
        var urlCall = $githubDiv.jzURL("CategoriesManagementController.add");
        $.ajax({
          type: 'GET',
          url: urlCall,
          data: {
            "title": self.title,
            "description": self.description,
            "icon": self.icon
          },

        }).done(function (data) {
          self.successMessage = "Category has been added successfully.";
          self.showSuccessMessage = true;
        }).fail(function () {
          self.errorMessage = "Error when adding category. Please try again!";
          self.showErrorMessage = true;
        }).always(function (data) {
          self.showSuccessMessage = false;
          self.showErrorMessage = false;
        });

      },
      updateCategory: function () {
        var self = this;
        var $githubDiv = $("#categories-management");
        var urlCall = $githubDiv.jzURL("CategoriesManagementController.update");
        $.ajax({
          type: 'GET',
          url: urlCall,
          data: {
            "id": self.id,
            "title": self.title,
            "description": self.description,
            "icon": self.icon
          },

        }).done(function (data) {
          self.successMessage = "Category has been updated successfully.";
          self.showSuccessMessage = true;
        }).fail(function () {
          self.errorMessage = "Error when adding category. Please try again!";
          self.showErrorMessage = true;
        }).always(function (data) {
          self.showSuccessMessage = false;
          self.showErrorMessage = false;
        });

      },
      activeEditForm: function (category) {
        var self = this;
        self.title = category.title;
        self.description = category.description;
        self.icon = category.icon;
        self.id = category.id;
        self.isNew = false;

      }

    },
    created() {
      var self = this;
      var $githubDiv = $("#categories-management");
      var createURL = $githubDiv.jzURL("CategoriesManagementController.init");
      //--- init properties
      self.showSuccessMessage = false;
      self.showErrorMessage = false;
      $.ajax({
        type: 'GET',
        url: createURL,
        success: function (data) {
          // Reload project tree;
          self.categories = data
        },
        error: function (xhr) {
          if (xhr.status >= 400) {
            console.log(xhr.responseText);
          } else {
            alert('error while create new project. Please try again.');
          }
        }
      });
    },
    mounted() {
      var self = this;
      var $githubDiv = $("#categories-management");
      var urlCall = $githubDiv.jzURL("CategoriesManagementController.getBundle") + "&locale=" + eXo.env.portal.language ;
      $.ajax({
        type: 'GET',
        url: urlCall,

      }).done(function (data) {
        self.i18n = data;
      }).fail(function () {
        console.warn('cannot load resource bundle')
      }).always(function (data) {

      });

    }

  });

})(jQuery)