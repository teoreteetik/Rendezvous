module.exports = function ( grunt ) {
  grunt.initConfig( {
    war: {
      target: {
        options: {
          war_dist_folder: 'war',
          war_verbose: true,
          war_name: 'swagger',
          webxml_welcome: 'index.html',
          webxml_display_name: 'Swagger',
          webxml_mime_mapping: [
            { extension: 'woff', mime_type: 'application/font-woff' }
          ]
        },
        files: [
          {
            expand: true,
            src: ['web/**']
          }
        ]
      }
    }
  } );
  grunt.loadNpmTasks( 'grunt-war' );
};