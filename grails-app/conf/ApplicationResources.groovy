def dev = grails.util.GrailsUtil.isDevelopmentEnv()

modules = {

	select2 {
		resource url: 'css/select2.css'
		resource url:'js/select2.js'
	}


	/** application wide resources */
	application {
		dependsOn 'select2'

		resource url:'js/date.js'

		resource url:'js/jquery.validate.js'
		resource url:'js/jquery.validate.bootstrap.js'

		resource url: 'css/datepicker.css'
		resource url:'js/bootstrap-datepicker.js'

		resource url: 'css/application.css'
		resource url: 'js/application.js'
	}

	/** language specific resources */

	application_en { dependsOn 'application' }

	/** nl specific resources */
	application_nl {
		dependsOn 'application'

		resource url:'js/nl/date-nl-NL.js'
		resource url:'js/nl/jquery.validate.bootstrap.nl.js'
		resource url:'js/nl/application-nl.js'
	}

	/** page specific resources */
	test_index {
		dependsOn 'application'

		resource url:'js/test-index.js'
	}

	crumbs {
		resource url: 'plugins/breadcrumbs-0.5.2/js/jquery.easing.js'
		resource url: 'plugins/breadcrumbs-0.5.2/js/jquery.jBreadCrumb.js'
		resource url: 'plugins/breadcrumbs-0.5.2/css/BreadCrumb.css'
	}
}
