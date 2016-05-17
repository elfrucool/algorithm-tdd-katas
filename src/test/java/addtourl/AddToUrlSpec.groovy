package addtourl

import spock.lang.Specification
import spock.lang.Unroll


class AddToUrlSpec extends Specification {
    @Unroll
    def "add #parameters to #path"() {
        given: "a #path and an array of parameters #parameters"

        when: "addQueryParametersToUrl() is called"
        def result = AddToUrl.addQueryParametersToUrl(path, parameters)

        then: "the new path should include the parameters"
        expected == result

        where:
        path      | parameters                   || expected
        "/a"      | [:]                          || "/a"
        "/a"      | ["foo": "bar"]               || "/a?foo=bar"
        "/a?"     | ["foo": "bar"]               || "/a?foo=bar"
        "/a?a=b"  | ["foo": "bar"]               || "/a?a=b&foo=bar"
        "/a?a=b&" | ["foo": "bar"]               || "/a?a=b&foo=bar"
        "/a"      | ["foo": "bar", "baz": "ups"] || "/a?foo=bar&baz=ups"
        "/a?"     | ["foo": "bar", "baz": "ups"] || "/a?foo=bar&baz=ups"
        "/a?a=b"  | ["foo": "bar", "baz": "ups"] || "/a?a=b&foo=bar&baz=ups"
        "/a?a=b&" | ["foo": "bar", "baz": "ups"] || "/a?a=b&foo=bar&baz=ups"
    }
}