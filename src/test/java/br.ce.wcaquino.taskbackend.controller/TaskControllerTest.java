package br.ce.wcaquino.taskbackend.controller;

    import br.ce.wcaquino.taskbackend.model.Task;
    import br.ce.wcaquino.taskbackend.repo.TaskRepo;
    import br.ce.wcaquino.taskbackend.utils.ValidationException;
    import java.time.LocalDate;
    import org.junit.Assert;
    import org.junit.Before;
    import org.junit.Test;
    import org.mockito.InjectMocks;
    import org.mockito.Mock;
    import org.mockito.Mockito;
    import org.mockito.MockitoAnnotations;

public class TaskControllerTest {

  @Mock
  private TaskRepo taskRepo;

  @InjectMocks
  private TaskController controller;

  @Before
  public void setup() {
    MockitoAnnotations.initMocks(this);
  }

  @Test
  public void shouldNotSaveTasksWithoutDescription() {
    Task task = new Task();
    task.setDueDate(LocalDate.now());

    try {
      controller.save(task);
    } catch (ValidationException e) {
      Assert.assertEquals("Fill the task description", e.getMessage());
    }
  }

  @Test
  public void shouldNotSaveTasksWithoutDate() {
    Task task = new Task();
    task.setTask("Description");

    try {
      controller.save(task);
    } catch (ValidationException e) {
      Assert.assertEquals("Fill the due date", e.getMessage());
    }
  }

  @Test
  public void shouldNotSaveTasksWithPastDates() {
    Task task = new Task();
    task.setTask("Description");
    task.setDueDate(LocalDate.of(2005, 10, 01));

    try {
      controller.save(task);
    } catch (ValidationException e) {
      Assert.assertEquals("Due date must not be in past", e.getMessage());
    }
  }

  @Test
  public void shouldSaveTaskWithSuccess() throws ValidationException {
    Task task = new Task();
    task.setTask("Description");
    task.setDueDate(LocalDate.now());

    controller.save(task);
    Mockito.verify(taskRepo).save(task);

  }
}
