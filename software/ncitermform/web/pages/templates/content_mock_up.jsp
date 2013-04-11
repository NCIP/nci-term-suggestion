<%--L
  Copyright Northrop Grumman Information Technology.

  Distributed under the OSI-approved BSD 3-Clause License.
  See http://ncip.github.com/nci-term-suggestion/LICENSE.txt for details.
L--%>

<%@ page import="gov.nih.nci.evs.browser.webapp.*" %>
<%
  String pagesPath = FormUtils.getPagesPath(request);
  String changeRequestForm = pagesPath + "/change_request.jsp";
  String newConceptForm = pagesPath + "/new_concept.jsf";
  String definitionChangeForm = pagesPath + "/modify_concept_mockup.jsf?property=Definition";
  String synonymChangeForm = pagesPath + "/modify_concept_mockup.jsf?property=Synonym";
  String othersChangeForm = pagesPath + "/modify_concept_mockup.jsf?property=Others";
%>
<div class="texttitle-blue">Mock Up Forms for Suggest New Term:</div><br/>
<p>
The following are samples mock up forms to request (or suggest)
a change for a particular vocabulary.  Currently, there are no
interactions between these pages.  They are currently used to
generate discussions to help us solidify our requirements.
</p>

<p>
The first form (or page) the user see will be the:
  <ul><li><a href="<%=changeRequestForm%>">Change Request</a></li></ul>
This will prompt the user for their contact information.  Once the user
selects the request type and then selects the "Next" button, this will
send the user to one of the following forms:
  <ul>
    <li><a href="<%=newConceptForm%>">New Concept</a></li>
    <li>Concept Modification Examples:
      <ul>
        <li><a href="<%=definitionChangeForm%>">Definition</a>,</li>
        <li><a href="<%=synonymChangeForm%>">Synonym</a>,</li>
        <li><a href="<%=othersChangeForm%>">Others</a></li>
      </ul>

      <br/>Note(s): The user needs to enter in vocabulary, concept code,
      and a property he/she would like to edit.  There will be
      a couple of dynamic interaction within this page:

      <ul>
        <li>Once the vocabulary is selected, this form will
            generate a list of editable properties (Property
            ComboBox) for the specified concept code.  We
            could limit this list to just definitions and
            synonyms.</li>
        <li>After the user enters a concept code, the Display
            URL will be updated to allow the user to browser
            the concept within a specific vocabulary.
            Example: If NCI Thesaurus is selected within the
            Vocabulary ComboBox, then the concept will be
            display within a separate NCIt browser window.
        </li>
        <li>When a specific property type is selected, the
            rest of the form will be update to allow the user
            to suggest adding a new property; or, suggest
            modifying or deleting a specific property.</li>
        <li>Selecting a specific property will propagate it to
            the "Suggest a new [PROPERTY_NAME] or modify an
            existing one:" textfield.</li>
        <li>Selecting the Delete radio button, will clear any
            previous suggestive text, and copies the current
            selected property value into this textfield.</li>
        <li><b>Only one property change per submission.</b></li>
      </ul>
    </li>
  </ul>
</p>
