module.exports = function ( grunt ) {

    // Project configuration.
    grunt.initConfig( {

        war: {
            target: {
                options: {
                    war_dist_folder: 'warbuild',
                    war_verbose: true,
                    war_name: 'webmagic',
                    webxml_welcome: 'index.html',
                    webxml_display_name: 'Web Magic',
                    webxml_mime_mapping: [
                        { extension: 'woff', mime_type: 'application/font-woff' }
                    ]
                },
                files: [
                    {
                        expand: true,
                        src: ['./**']
                    }
                ]
            }
        }

    } );

    grunt.loadNpmTasks( 'grunt-war' );

};