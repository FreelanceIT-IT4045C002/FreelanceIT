package com.freelanceit.freelanceit;

import com.freelanceit.freelanceit.dao.IProjectDAO;
import com.freelanceit.freelanceit.dto.Project;
import org.mockito.MockitoAnnotations;
import com.freelanceit.freelanceit.service.ProjectService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProjectServiceTests {

    @Mock
    private IProjectDAO projectDAO; // Mock the DAO

    @InjectMocks
    private ProjectService projectService; // Inject the mock into the ProjectService

    @BeforeEach
    void setUp() {
        // Initialize mocks before each test
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSaveProject() throws Exception {
        // Given: A project to save
        Project project = new Project();

        // When: The save method is called
        when(projectDAO.save(project)).thenReturn(project); // Mock the save method

        Project savedProject = projectService.save(project); // Call the method to test

        // Then: Verify the project is saved and the correct project is returned
        assertNotNull(savedProject);
        assertEquals(1, savedProject);
        assertEquals("Test Project", savedProject.getTitle());

        // Verify that the save method of the DAO was called once with the project
        verify(projectDAO, times(1)).save(project);
    }
}
