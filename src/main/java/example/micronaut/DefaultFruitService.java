/*
 * Copyright 2017-2024 original authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package example.micronaut;

import io.micronaut.core.annotation.NonNull;
import io.micronaut.data.model.Page;
import io.micronaut.data.model.Pageable;
import io.micronaut.data.model.Slice;
import jakarta.inject.Singleton;

import java.util.List;
import java.util.Optional;

@Singleton // <1>
class DefaultFruitService implements FruitService {

    private final FruitRepository fruitRepository;

    public DefaultFruitService(FruitRepository fruitRepository) {
        this.fruitRepository = fruitRepository;
    }

    public Iterable<Fruit> list() {
        return fruitRepository.findAll();
    }

    public Fruit save(Fruit fruit) {
        if (fruit.getId() == null) {
            return fruitRepository.save(fruit);
        } else {
            return fruitRepository.update(fruit);
        }
    }

    public Optional<Fruit> find(@NonNull String id) {
        return fruitRepository.findById(id);
    }

    public Iterable<Fruit> findByNameInList(List<String> name) {
        return fruitRepository.findByNameInList(name);
    }

    @Override
    public Page<Fruit> list(Pageable pageable) {
        return fruitRepository.list(pageable);
    }
}
