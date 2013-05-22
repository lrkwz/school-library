package it.lrkwz.school.library



import org.junit.*
import grails.test.mixin.*

@TestFor(StudentController)
@Mock(Student)
class StudentControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/reader/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.readerInstanceList.size() == 0
        assert model.readerInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.readerInstance != null
    }

    void testSave() {
        controller.save()

        assert model.readerInstance != null
        assert view == '/reader/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/reader/show/1'
        assert controller.flash.message != null
        assert Student.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/reader/list'

        populateValidParams(params)
        def reader = new Student(params)

        assert reader.save() != null

        params.id = reader.id

        def model = controller.show()

        assert model.readerInstance == reader
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/reader/list'

        populateValidParams(params)
        def reader = new Student(params)

        assert reader.save() != null

        params.id = reader.id

        def model = controller.edit()

        assert model.readerInstance == reader
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/reader/list'

        response.reset()

        populateValidParams(params)
        def reader = new Student(params)

        assert reader.save() != null

        // test invalid parameters in update
        params.id = reader.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/reader/edit"
        assert model.readerInstance != null

        reader.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/reader/show/$reader.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        reader.clearErrors()

        populateValidParams(params)
        params.id = reader.id
        params.version = -1
        controller.update()

        assert view == "/reader/edit"
        assert model.readerInstance != null
        assert model.readerInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/reader/list'

        response.reset()

        populateValidParams(params)
        def reader = new Student(params)

        assert reader.save() != null
        assert Student.count() == 1

        params.id = reader.id

        controller.delete()

        assert Student.count() == 0
        assert Student.get(reader.id) == null
        assert response.redirectedUrl == '/reader/list'
    }
}
