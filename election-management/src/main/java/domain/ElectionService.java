package domain;

import domain.annotations.SQL;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Any;
import jakarta.enterprise.inject.Instance;

import java.util.List;

@ApplicationScoped
public class ElectionService {
    private ElectionRepository repository;
    private final Instance<ElectionRepository> repositories;
    private final CandidateService candidateService;

    public ElectionService(@Any Instance<ElectionRepository> repositories, CandidateService candidateService,
                           @SQL ElectionRepository repository) {
        this.repositories = repositories;
        this.candidateService = candidateService;
        this.repository = repository;
    }

    public void submit() {
        Election election = Election.create(candidateService.findAll());
        repositories.forEach(repository -> repository.submit(election));
    }

    public List<Election> findAll() {
        return repository.findAll();
    }
}
