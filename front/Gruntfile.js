module.exports = function (grunt) {
  grunt.initConfig( {
    clean: ["build"],
    uglify: {
      external: {
        files: { 'build/dist/js/external.js': ['build/dist/js/external.js'] }
      },
      internal: {
        files: { 'build/dist/js/internal.js': ['build/dist/js/internal.js'] }
      }
    },
    concat: {
      external: {
        options: {
          separator: ';\n'
        },
        src: ['bower_components/jquery/dist/jquery.min.js',
              'bower_components/angular/angular.min.js',
              'bower_components/angular-cookies/angular-cookies.min.js',
              'bower_components/angular-route/angular-route.min.js',
              'bower_components/angular-resource/angular-resource.min.js',
              'bower_components/restangular/dist/restangular.min.js',
              'bower_components/underscore/underscore.js',
              'bower_components/marked/lib/marked.js',
              'bower_components/select2/select2.min.js',
              'bower_components/angular-ui-select2/src/select2.js',
              'bower_components/angular-ui-router/release/angular-ui-router.min.js'
            ],
        dest: 'build/dist/js/external.js',
        nonull: true
      },
      internal: {
        options: { separator: ';\n' },
        src: ['js/*.js',
              'js/services/*.js',
              'js/controllers/*.js',
              'js/directives/*.js'
        ],
        dest: 'build/dist/js/internal.js',
        nonull: true
      }
    },
    cssmin: {
      combine: {
        files: {
          'build/dist/css/master.css': ['css/bootstrap.min.css',
                                        'bower_components/select2/select2.css',
                                        'css/custom.css']
        }
      }
    },
    copy: {
      main: {
        files: [
          { src: ['templates/**'], dest: 'build/dist/' },
          { src: ['index.html'], dest: 'build/dist/' },
          { src: ['auth/**'], dest: 'build/dist/' },
          { flatten:true, expand:true, src: ['bower_components/select2/select2.png','bower_components/select2/select2-spinner.gif'], dest: 'build/dist/css/' },
          { flatten: true, expand: true, src: ['fonts/**'], dest: 'build/dist/fonts/' }
        ]
      }
    },
    htmlmin: {
      dist: {
        options: {
          removeComments: true,
          collapseWhitespace: true
        },
        files: {
          'build/dist/index.html': 'build/dist/index.html'
        }
      }
    },
    war: {
      target: {
        options: {
          war_dist_folder: 'war',
          war_verbose: true,
          war_name: 'front',
          webxml_welcome: 'index.html',
          webxml_display_name: 'Rendezvous',
          webxml_webapp_extras: ['<filter><filter-name>ExpiresFilter</filter-name><filter-class>org.apache.catalina.filters.ExpiresFilter</filter-class><init-param><param-name>ExpiresByType image</param-name><param-value>access plus 10 minutes</param-value></init-param><init-param><param-name>ExpiresByType text/css</param-name><param-value>access plus 10 minutes</param-value></init-param><init-param><param-name>ExpiresByType application/javascript</param-name><param-value>access plus 10 minutes</param-value></init-param></filter><filter-mapping><filter-name>ExpiresFilter</filter-name><url-pattern>/*</url-pattern><dispatcher>REQUEST</dispatcher></filter-mapping>'],
          webxml_mime_mapping: [
            { extension: 'woff', mime_type: 'application/font-woff' }
          ]
        },
        files: [
          {
            expand: true,
            src: ['build/dist/**']
          }
        ]
      }
    },
    watch: {
      scripts: {
        files: ['js/**'],
        tasks: ['concat:internal'],
        options: {
          spawn: false
        }
      }
    }
  });
  grunt.loadNpmTasks('grunt-war');
  grunt.loadNpmTasks('grunt-contrib-uglify');
  grunt.loadNpmTasks('grunt-contrib-concat');
  grunt.loadNpmTasks('grunt-contrib-copy');
  grunt.loadNpmTasks('grunt-contrib-htmlmin');
  grunt.loadNpmTasks('grunt-contrib-cssmin');
  grunt.loadNpmTasks('grunt-contrib-watch');
  grunt.loadNpmTasks('grunt-contrib-clean');
};