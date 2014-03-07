module.exports = function ( grunt ) {
  grunt.initConfig( {
    war: {
      target: {
        options: {
          war_dist_folder: 'war',
          war_verbose: true,
          war_name: 'front',
          webxml_welcome: 'index.html',
          webxml_display_name: 'Rendezvous',
          webxml_mime_mapping: [
            { extension: 'woff', mime_type: 'application/font-woff' }
          ]
        },
        files: [
          {
            expand: true,
            src: ['app/**']
          }
        ]
      }
    }
  });
  grunt.loadNpmTasks( 'grunt-war' );
};