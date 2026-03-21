package com.ibc.training.fooddelivery.dao;

import com.ibc.training.fooddelivery.entity.MenuItem;
import com.ibc.training.fooddelivery.repository.MenuItemRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class MenuItemDaoTest {

    @Mock
    private MenuItemRepository menuItemRepository;

    @InjectMocks
    private MenuItemDao menuItemDao;

    private MenuItem menuItem;

    @BeforeEach
    public void setUp() {
        menuItem = new MenuItem();
        menuItem.setId(1);
        menuItem.setName("Pasta Carbonara");
        menuItem.setDescription("Classic Italian pasta");
        menuItem.setPrice(12.99);
    }

    @Test
    public void testSaveMenuItem() {
        when(menuItemRepository.save(any(MenuItem.class))).thenReturn(menuItem);

        MenuItem result = menuItemDao.save(menuItem);

        assertNotNull(result);
        assertEquals("Pasta Carbonara", result.getName());
        verify(menuItemRepository, times(1)).save(any(MenuItem.class));
    }

    @Test
    public void testFindMenuItemById() {
        when(menuItemRepository.findById(anyInt())).thenReturn(Optional.of(menuItem));

        Optional<MenuItem> result = menuItemDao.findById(1);

        assertTrue(result.isPresent());
        assertEquals("Pasta Carbonara", result.get().getName());
        verify(menuItemRepository, times(1)).findById(1);
    }

    /*@Test
    public void testFindAllMenuItems() {
        List<MenuItem> items = new ArrayList<>();
        items.add(menuItem);

        when(menuItemRepository.findAll()).thenReturn(items);

        List<MenuItem> result = menuItemDao.findAll();

        assertNotNull(result);
        assertEquals(1, result.size());
        verify(menuItemRepository, times(1)).findAll();
    }*/

    @Test
    public void testFindMenuItemsByRestaurantId() {
        List<MenuItem> items = new ArrayList<>();
        items.add(menuItem);

        when(menuItemRepository.findByRestaurantId(anyInt())).thenReturn(items);

        List<MenuItem> result = menuItemDao.findByRestaurantId(1);

        assertNotNull(result);
        assertEquals(1, result.size());
        verify(menuItemRepository, times(1)).findByRestaurantId(1);
    }

    @Test
    public void testDeleteMenuItem() {
        menuItemDao.deleteById(1);

        verify(menuItemRepository, times(1)).deleteById(1);
    }

    @Test
    public void testMenuItemNotFound() {
        when(menuItemRepository.findById(anyInt())).thenReturn(Optional.empty());

        Optional<MenuItem> result = menuItemDao.findById(999);

        assertFalse(result.isPresent());
    }
}

