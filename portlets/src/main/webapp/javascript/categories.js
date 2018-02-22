(function () {
  Vue.component('category', {
    props: ['item'],
    template: '<li>{{ item.title }}</li>'
  });

  var app7 = new Vue({
    el: '#categories-management',
    data: {
      categories: [
        {
          id: 0,
          title: 'Marketing',
          description: 'Marketing World',
          icon: 'marketing.png'
        },
        {
          id: 1,
          title: 'Engineering',
          description: 'Engineering World',
          icon: 'engineering.png'
        },
        {
          id: 2,
          title: 'Support',
          description: 'Support World',
          icon: 'support.png'
        }
      ]
    }
  });

})()