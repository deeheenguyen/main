package seedu.address.logic.commands;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.function.Predicate;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import javafx.collections.ObservableList;

import seedu.address.logic.CommandHistory;
import seedu.address.logic.UndoRedoStack;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.interview.Interview;
import seedu.address.model.interview.exceptions.DuplicateInterviewException;
import seedu.address.model.interview.exceptions.InterviewNotFoundException;
import seedu.address.model.job.Job;
import seedu.address.model.job.exceptions.DuplicateJobException;
import seedu.address.model.job.exceptions.JobNotFoundException;
import seedu.address.model.person.Person;
import seedu.address.model.person.exceptions.DuplicatePersonException;
import seedu.address.model.person.exceptions.PersonNotFoundException;
import seedu.address.model.report.Report;
import seedu.address.model.tag.Tag;
import seedu.address.testutil.ReportBuilder;

public class ViewReportCommandTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void constructor_nullReport_throwsNullPointerException() {
        thrown.expect(NullPointerException.class);
        new ViewReportCommand(null);
    }

    @Test
    public void execute_reportAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingReportUpdated modelStub = new ModelStubAcceptingReportUpdated();
        Tag validTag = new Tag("validTag");
        Report validReport = new ReportBuilder().build();

        CommandResult commandResult = getViewReportCommand(validTag, modelStub).execute();

        assertEquals(String.format(ViewReportCommand.MESSAGE_SUCCESS + validTag.tagName), commandResult.feedbackToUser);
        assertEquals(validReport, modelStub.report);
    }

    @Test
    public void equals() {
        Report screening = new ReportBuilder().withPopulation("Screening").build();
        Report interviewing = new ReportBuilder().withPopulation("Interviewing").build();
        ViewReportCommand viewreportScreeningCommand = new ViewReportCommand(new Tag("Screening"));
        ViewReportCommand viewreportInterviewingCommand = new ViewReportCommand(new Tag("Interviewing"));

        // same object -> returns true
        assertTrue(viewreportScreeningCommand.equals(viewreportScreeningCommand));

        // same values -> returns true
        ViewReportCommand addScreeningCommandCopy = new ViewReportCommand(new Tag("Screening"));
        assertTrue(viewreportScreeningCommand.equals(addScreeningCommandCopy));

        // different types -> returns false
        assertFalse(viewreportScreeningCommand.equals(1));

        // null -> returns false
        assertFalse(viewreportScreeningCommand.equals(null));

        // different report -> returns false
        assertFalse(viewreportScreeningCommand.equals(viewreportInterviewingCommand));
    }

    /**
     * Generates a new ViewReportCommand with the details of the given report.
     */
    private ViewReportCommand getViewReportCommand(Tag population, Model model) {
        ViewReportCommand command = new ViewReportCommand(population);
        command.setData(model, new CommandHistory(), new UndoRedoStack());
        return command;
    }

    /**
     * A default model stub that have all of the methods failing.
     */
    private class ModelStub implements Model {
        @Override
        public void addPerson(Person person) throws DuplicatePersonException {
            fail("This method should not be called.");
        }

        @Override
        public void addJob(Job job) throws DuplicateJobException {
            fail("This method should not be called.");
        }

        @Override
        public void addInterview(Interview interview) throws DuplicateInterviewException {
            fail("This method should not be called.");
        }

        @Override
        public void deleteInterview(Interview target) throws InterviewNotFoundException {
            fail("This method should not be called.");
        }

        @Override
        public void addReport(Report report) {

            fail("This method should not be called.");
        }

        @Override
        public void resetData(ReadOnlyAddressBook newData) {
            fail("This method should not be called.");
        }

        @Override
        public ReadOnlyAddressBook getAddressBook() {
            fail("This method should not be called.");
            return null;
        }

        @Override
        public void deletePerson(Person target) throws PersonNotFoundException {
            fail("This method should not be called.");
        }

        @Override
        public void updatePerson(Person target, Person editedReport)
                throws DuplicatePersonException {
            fail("This method should not be called.");
        }

        @Override
        public void updateReport(Tag population) {
            fail("This method should not be called.");
        }

        @Override
        public ObservableList<Person> getFilteredPersonList() {
            fail("This method should not be called.");
            return null;
        }

        @Override
        public Report getReport() {
            fail("This method should not be called.");
            return null;
        };

        @Override
        public void refreshReport() {
            fail("This method should not be called.");
        };

        @Override
        public ObservableList<Report> getReportHistory() {
            fail("This method should not be called.");
            return null;
        }

        @Override
        public void updateFilteredPersonList(Predicate<Person> predicate) {
            fail("This method should not be called.");
        }

        @Override
        public void updateFilteredJobList(Predicate<Job> predicate) {
            fail("This method should not be called.");
        }

        @Override
        public ObservableList<Job> getFilteredJobList() {
            fail("This method should not be called.");
            return null;
        }

        @Override
        public void deleteJob(Job target) throws JobNotFoundException {
            fail("This method should not be called.");
        }

        @Override
        public void updateJob(Job target, Job editedJob)
                throws DuplicateJobException {
            fail("This method should not be called.");
        }

        @Override
        public ObservableList<Interview> getFilteredInterviewList() {
            fail("this method should not be called");
            return null;
        }

        @Override
        public void updateFilteredInterviewList(Predicate<Interview> predicate) {
            fail("This method should not be called.");
        }

    }

    /**
     * A Model stub that always accept the report being added.
     */
    private class ModelStubAcceptingReportUpdated extends ModelStub {
        private Report report;

        @Override
        public void updateReport(Tag population) {
            report = new ReportBuilder().build();
        }


        @Override
        public Report getReport() {
            return report;
        }

        @Override
        public ReadOnlyAddressBook getAddressBook() {
            return new AddressBook();
        }
    }

}
