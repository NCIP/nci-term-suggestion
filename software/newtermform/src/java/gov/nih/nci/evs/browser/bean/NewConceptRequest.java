package gov.nih.nci.evs.browser.bean;

import gov.nih.nci.evs.browser.utils.*;

import javax.servlet.http.*;

public class NewConceptRequest extends RequestBase {
    private final String EMAIL = "email";
    private final String OTHER = "other";
    private final String VOCABULARY = "vocabulary";
    private final String TERM = "term";
    private final String SYNONYMS = "synonyms";
    private final String PARENT_CODE = "parentCode";
    private final String DEFINITION = "definition";
    private final String REASON = "reason";

    public NewConceptRequest(HttpServletRequest request) {
        super(request);
        setParameters(new String[] { EMAIL, OTHER, VOCABULARY, TERM, SYNONYMS,
                PARENT_CODE, DEFINITION, REASON });
    }

    public String submit() {
        _request.getSession().setAttribute("message",
                Utils.toHtml(formatEmailMessage()));
        updateAttributes();
        return "message";
    }

    protected String formatEmailMessage() {
        String indent = "    ";
        StringBuffer buffer = new StringBuffer();
        buffer.append("Request New Concept");
        for (int i = 0; i < _parameters.length; ++i) {
            String parameter = _parameters[i];
            buffer.append("\n" + indent + "* ");
            buffer.append(parameter);
            buffer.append(": ");
            buffer.append(_parametersHashMap.get(parameter));
        }
        return buffer.toString();
    }
}
