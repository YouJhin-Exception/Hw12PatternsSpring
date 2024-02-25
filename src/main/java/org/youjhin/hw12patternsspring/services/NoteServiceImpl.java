package org.youjhin.hw12patternsspring.services;


import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.youjhin.hw12patternsspring.enums.Status;
import org.youjhin.hw12patternsspring.enums.Urgency;
import org.youjhin.hw12patternsspring.model.NoteEntity;
import org.youjhin.hw12patternsspring.notifications.EmailNotificationObserver;
import org.youjhin.hw12patternsspring.notifications.LoggingObserver;
import org.youjhin.hw12patternsspring.repository.NoteRepository;
import org.youjhin.hw12patternsspring.services.factories.NoteFactory;
import org.youjhin.hw12patternsspring.services.factories.RegularNoteFactory;
import org.youjhin.hw12patternsspring.services.factories.UrgentNoteFactory;
import org.youjhin.hw12patternsspring.services.interfaces.NoteService;
import org.youjhin.hw12patternsspring.services.observer.Observer;
import org.youjhin.hw12patternsspring.services.observer.Subject;


import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

/**
 * Реализация интерфейса NoteService.
 * Предоставляет методы для взаимодействия с объектами NoteEntity.
 */
@Service
public class NoteServiceImpl implements NoteService, Subject {


    /*  Применение Singleton

    private static NoteServiceImpl instance; // Шаг 1: Статический экземпляр

    private final NoteRepository noteRepository;

    // Шаг 2: Приватный конструктор
    private NoteServiceImpl(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    // Шаг 3: Публичный статический метод для получения экземпляра
    public static NoteServiceImpl getInstance(NoteRepository noteRepository) {
        if (instance == null) {
            instance = new NoteServiceImpl(noteRepository);
        }
        return instance;
    }

    НО! класс NoteServiceImpl уже аннотирован с @Service и использует внедрение зависимостей через конструктор
    (или через поля с аннотацией @Autowired), то Spring автоматически управляет его экземпляром как Singleton.
    Это стандартное поведение для Spring бинов, которые объявлены через аннотации компонентов
    (@Component, @Service, @Repository, @Controller и др.).
    Spring гарантирует, что будет создан только один экземпляр этого сервиса и будет использовать его везде,
    где требуется внедрение зависимости этого типа.

    Таким образом, мой класс уже соответствует паттерну Singleton в контексте Spring Framework.

     */

    private final List<Observer> observers = new ArrayList<>(); // лист наблюдателей

    private final NoteRepository noteRepository;

    /**
     * Конструирует NoteServiceImpl с указанным NoteRepository.
     * @param noteRepository Репозиторий для управления объектами NoteEntity.
     */
    @Autowired
    public NoteServiceImpl(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;

        //добавляем наблюдателей за заметками
        registerObserver(new EmailNotificationObserver());
        registerObserver(new LoggingObserver());
    }

    /**
     * Получает все заметки из репозитория.
     * @return Список всех заметок.
     */
    @Override
    public List<NoteEntity> getAllNotes() {
        return noteRepository.findAll();
    }

    /**
     * Получает заметки из репозитория на основе их статуса.
     * @param status Статус заметок для получения.
     * @return Список заметок с указанным статусом.
     */
    @Override
    public List<NoteEntity> getNotesByStatus(Status status) {
        return noteRepository.findByStatus(status);
    }


    /*
      Новый метод использующий фабрику для создания заметок
     */
    @Override
    public void createNote(String description, Urgency urgency) {
        NoteFactory factory = (urgency == Urgency.URGENT) ? new UrgentNoteFactory() : new RegularNoteFactory();
        NoteEntity note = factory.createNote(description,urgency);
        note.setStatus(Status.NOT_STARTED); // По умолчанию статус NOT_STARTED

        //уведомляем о создании
        notifyObservers("Добавлена новая заметка: " + note);

        noteRepository.save(note);
    }


    /**
     * Обновляет статус заметки с указанным ID.
     *
     * @param id        ID заметки для обновления.
     * @param newStatus Новый статус для установки заметке.
     * @throws EntityNotFoundException Если заметка с указанным ID не найдена.
     */
    @Override
    public void updateStatusNote(Long id, Status newStatus) {
        Optional<NoteEntity> findNote = noteRepository.findById(id);

        if (findNote.isPresent()) {
            NoteEntity note = findNote.get();
            note.setStatus(newStatus);
            noteRepository.save(note);

            //уведомляем об изменении
            notifyObservers("Обновлена заметка: " + note);

        } else {
            throw new EntityNotFoundException("Заметка с ID " + id + " не найдена");
        }
    }

    /**
     * Удаляет заметку с указанным ID.
     * @param id ID заметки для удаления.
     */
    @Override
    public void deleteNote(Long id) {
        noteRepository.deleteById(id);

        // уведомляем об удалении
        notifyObservers("Удалена заметка с id: " + id);
    }

    /**
     * Находим заметку с указанным ID.
     * @param noteId ID заметки для поиска.
     * @throws NoSuchElementException Если заметка не найдена.
     */
    @Override
    public NoteEntity findById(Long noteId) {
        return noteRepository.findById(noteId)
                .orElseThrow(() -> new NoSuchElementException("Note with id: " + noteId + " was not found."));
    }


    // методы для Observers

    @Override
    public void registerObserver(Observer o) {
        observers.add(o);
    }

    @Override
    public void removeObserver(Observer o) {
        observers.remove(o);
    }

    @Override
    public void notifyObservers(Object obj) {
        for (Observer observer : observers) {
            observer.update(obj); // Здесь можно передавать детали изменения
        }
    }

}