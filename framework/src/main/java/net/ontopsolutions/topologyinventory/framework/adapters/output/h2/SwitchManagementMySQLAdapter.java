package net.ontopsolutions.topologyinventory.framework.adapters.output.h2;

import net.ontopsolutions.topologyinventory.application.ports.output.SwitchManagementOutputPort;
import net.ontopsolutions.topologyinventory.domain.entity.Switch;
import net.ontopsolutions.topologyinventory.domain.vo.Id;

import net.ontopsolutions.topologyinventory.framework.adapters.output.h2.mappers.RouterMapper;
import net.ontopsolutions.topologyinventory.framework.adapters.output.h2.repository.SwitchManagementRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class SwitchManagementMySQLAdapter implements SwitchManagementOutputPort {

    @Inject
    SwitchManagementRepository switchManagementRepository;

    @Override
    public Switch retrieveSwitch(Id id) {
        var switchData = switchManagementRepository.findById(id.getId()).subscribe().asCompletionStage().join();
        return RouterMapper.switchDataToDomain(switchData);
    }
}
