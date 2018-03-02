(function ($) {
  Vue.component('category', {
    props: ['item'],
    template: '<li>{{ item.title }}</li>'
  });

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
      showErrorMessage: false
    },
    methods: {
      deleteCategory: function (index, category) {
        var self = this;
        var $githubDiv = $("#categories-management");
        var urlCall = $githubDiv.jzURL("CategoriesManagementController.delete");

        $.ajax({
          type: 'GET',
          url: urlCall,
          data: category,

        }).done(function () {
          self.successMessage = "Category has been removed successfully.";
          self.showSuccessMessage = true;
        }).fail(function () {
          self.errorMessage = "Error when dropping category. Please try again!";
          self.showErrorMessage = true
        }).always(function () {
          self.categories.splice(index, 1);
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
          this.categories.push(data);

        });

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
          console.log(data);
        },
        error: function (xhr) {
          if (xhr.status >= 400) {
            console.log(xhr.responseText);
          } else {
            alert('error while create new project. Please try again.');
          }
        }
      });
    }

  });

})(jQuery)