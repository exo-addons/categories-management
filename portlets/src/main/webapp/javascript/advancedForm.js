(function () {
Vue.component('form-input', {
  props: ['question'],
  template: '<div class="form-group" :class="{\'input\': true, \'has-error\': errors.has(question.label) }">' +
    '<input type="text" v-validate="question.validate" :id="question.id" :name="question.label"'+
           'class="form-control" v-model="question.answer":placeholder="question.placeholder"/>' +
    '    <span v-show="errors.has(question.label)" class="help-block">{{ errors.first(question.label) }}</span>' +
    '</div>'
});

Vue.component('form-select', {
    props: ['question'],
    created: function () {
        this.$options.template = '<div class="form-group" :class="{\'input\': true, \'has-error\': errors.has(question.label) }">' +
        '<select v-validate="question.validate" :id="question.id" :name="question.label" v-model="question.answer" class="form-control" >' +
        '<option v-for="option in question.options" >{{option}}</option>' +
        '</select>' +
        '<span v-show="errors.has(question.label)" class="help-block">{{ errors.first(question.label) }}</span>' +
        '</div>'
    }
});

Vue.component('form-radio', {
  props: ['question'],
  created: function() {
    this.$options.template = '<div class="col-sm-9"> ' +
      '<label  v-for="option in question.options" :for="option" class="radio-inline" >' +
      '<input :id="option" :name="question.label" type="radio" v-model="question.answer"' +
      '       :value="option"/> {{option}}' +
      '</label>' +
      '</div>';
  }
});

Vue.component('form-textarea', {
  props: ['question'],
  template: '<textarea v-validate="question.validate" class="form-control" placeholder="" v-model="question.answer"  :placeholder="question.placeholder"/>'
});

Vue.component('form-question', {
  props: ['question'],
  created: function() {
    this.$options.template = '<div class="form-group"> ' +
      '<label for="" class="col-sm-3 col-lg-2 control-label">' +
      '{{question.label}}';
    if ((this.question.validate !== undefined) && this.question.validate.match("required")) {
      this.$options.template += '<em>*</em>'
    }
    this.$options.template += '</label>' +
      '<div class="col-sm-9 col-lg-10">';
    switch (this.question.type) {
      case 'input':
        this.$options.template += '<form-input :question="question"></form-input>';
        break;
      case 'select':
        this.$options.template += '<form-select :question="question"></form-select>';
        break;
      case 'radio':
        this.$options.template += '<form-radio :question="question"></form-radio>';
        break;
      case 'textarea':
        this.$options.template += '<form-textarea :question="question"></form-textarea>';
        break;
      default:
        this.$options.template += 'Unsupported question type: ' + this.question.type;
    }
    this.$options.template += '</div></div>';
  }
});

Vue.use(VeeValidate);

// bootstrap the application
var app = new Vue({
  el: '#dynform',
  data: {
    questions: []
  },
  created: function() {
    // Dynamic Form could be load from a REST API
    this.questions.push(
        {
              id: 1,
              label: 'First Name',
              type: 'input',
              answer: 'Antoine',
              validate: "required|alpha"
        },
        {
              id: 2,
              label: 'Last Name',
              type: 'input',
              validate: "required|alpha|min:2"
        },
        {
              id: 3,
              label: 'Email',
              type: 'input',
              validate: "required|email"
        },
        {
              id: 4,
              label: 'Job',
              type: 'select',
              options: ['...', 'Developer', 'Ops', 'Project Manager'],
              validate: 'in:Developer,Ops,Project Manager',
              answer: 'Developer'
        },
        {
              id: 5,
              label: 'Gender',
              type: 'radio',
              options: ['Male', 'Female'],
              answer: 'Male'
        },
        {
              id: 6,
              label: 'Address',
              type: 'textarea',
              placeholder: 'Your Pincode and City'
        }
    );
  },
  methods: {
    displayForm: function(event) {
      var $this = this;
      var $validator = this.$validator;
      var data = {};
      this.questions.forEach(function(question) {
        if (question.validate !== undefined) {
          $validator.attach(question.label, question.validate);
          data[question.label] = question.answer;
        }
      });
      var $questions = this.questions;
      $validator.validateAll(data).then(function() {
        var form = [];
        $questions.forEach(function(question) {
          form.push({
            id: question.id,
            label: question.label,
            answer: question.answer
          });
        });
        alert("Valid form: "+JSON.stringify(form));
      }).catch(function(error) {
          $this.$children.forEach(function(child) {
        	child.$children.forEach(function(child) {
          	child.$validator.validateAll();
          });
        });
        alert("Invalid form. Error count:  " + $validator.getErrors().count());
      })
    }
  }
});

}) ()