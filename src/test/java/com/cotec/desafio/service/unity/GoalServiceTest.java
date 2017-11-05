package com.cotec.desafio.service.unity;

import com.cotec.desafio.exception.InvalidEntityException;
import com.cotec.desafio.model.Goal;
import com.cotec.desafio.model.GoalType;
import com.cotec.desafio.model.Organization;
import com.cotec.desafio.repository.GoalRepository;
import com.cotec.desafio.service.GoalService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class GoalServiceTest {

    @InjectMocks
    private GoalService goalService;

    @Mock
    private GoalRepository goalRepository;

    private Organization organization1;

    private Organization organization2;

    private Goal goal1;

    private Goal goal2;
    private Goal goal3;

    @Before
    public void setUp(){
        createOrganizations();
    }

    private void createOrganizations() {
        organization1 = new Organization();
        organization1.setDescription("TestOrganization1");
        organization1.setId(10000l);

        organization2 = new Organization();
        organization2.setDescription("TestOrganization2");
        organization2.setId(10001l);
    }

    @Test
    public void itShouldSaveGoal(){
        givenGoal1();
        whenTryToCreateGoal();
        thenShouldSave();
    }

    @Test
    public void itShouldSaveRelatedGoals(){
        givenGoal1();
        givenGoal2();
        whenTryToRelateGoals();
        thenShouldSave();
    }

    @Test(expected = InvalidEntityException.class)
    public void itShouldNotSaveRelatedGoals(){
        givenGoal1();
        givenGoal3(organization2, goal1);
        whenTryToRelateWrongGoals();
    }

    @Test(expected = InvalidEntityException.class)
    public void itShouldNotSaveCycleRelatedGoals(){
        givenGoal1();
        givenGoal2();
        givenGoal3(organization1, goal2);
        whenTryToCycleRelateGoals();
    }

    private void givenGoal1() {
        goal1 = new Goal();
        goal1.setId(10000l);
        goal1.setOrganization(organization1);
        goal1.setType(GoalType.IMPORTANTE);
        goal1.setDescription("TestGoal1");
    }

    private void whenTryToCycleRelateGoals() {
        goal1.setGoal(goal3);

        when(goalRepository.findOne(goal1.getId())).thenReturn(goal1);
        when(goalRepository.findOne(goal2.getId())).thenReturn(goal2);
        when(goalRepository.findOne(goal3.getId())).thenReturn(goal3);
        goalService.save(goal1);
    }

    private void whenTryToRelateWrongGoals() {
        when(goalRepository.findOne(anyLong())).thenReturn(goal2);
        goalService.save(goal3);
    }

    private void givenGoal3(Organization organization, Goal goal) {
        goal3 = new Goal();
        goal3.setId(10002l);
        goal3.setOrganization(organization);
        goal3.setType(GoalType.GERAL);
        goal3.setDescription("TestGoal3");
        goal3.setGoal(goal);
    }

    private void whenTryToRelateGoals() {
        when(goalRepository.findOne(anyLong())).thenReturn(goal1);
        goalService.save(goal2);
    }

    private void givenGoal2() {
        goal2 = new Goal();
        goal2.setId(10001l);
        goal2.setOrganization(organization1);
        goal2.setType(GoalType.TRIVIAL);
        goal2.setDescription("TestGoal2");
        goal2.setGoal(goal1);
    }

    private void whenTryToCreateGoal() {
        goalService.save(goal1);
    }

    private void thenShouldSave() {
        verify(goalRepository).save(any(Goal.class));
    }

}