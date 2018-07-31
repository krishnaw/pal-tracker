package io.pivotal.pal.tracker;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/time-entries")
public class TimeEntryController {

    private TimeEntryRepository timeEntriesRepo;

    public TimeEntryController(TimeEntryRepository timeEntryRepository) {
        this.timeEntriesRepo = timeEntryRepository;
    }

    @PostMapping
    public ResponseEntity create(@RequestBody TimeEntry timeEntryToCreate) {
        TimeEntry timeEntry = timeEntriesRepo.create(timeEntryToCreate);

        return new ResponseEntity<> (timeEntry, HttpStatus.CREATED);
    }


    @PutMapping("{id}")
    public ResponseEntity update(@PathVariable Long id, @RequestBody TimeEntry timeEntry) {
        TimeEntry updatedTimeEntry = timeEntriesRepo.update(id, timeEntry);
        if (updatedTimeEntry != null) {
            return new ResponseEntity<>(updatedTimeEntry, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping ("{id}")
    public ResponseEntity<TimeEntry> delete(@PathVariable Long id)    {
            timeEntriesRepo.delete(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("{id}")
    public ResponseEntity<TimeEntry> read(@PathVariable Long id) {
        TimeEntry timeEntry = timeEntriesRepo.find(id);
        if (timeEntry != null) {
            return new ResponseEntity<>(timeEntry, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<List<TimeEntry>> list() {
        return new ResponseEntity<>(timeEntriesRepo.list(), HttpStatus.OK);

    }
}
