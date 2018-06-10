/**
 * 
 */
package com.gul.farmerbroker.common;

import org.springframework.data.domain.Persistable;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author Lynn
 *
 */
@Data
@EqualsAndHashCode(of = "id", callSuper = false)
public abstract class BaseModel implements Persistable<Long> {
	private Long id;
	private boolean newObj;

	public void setNew(boolean newObj) {
		this.newObj = newObj;
	}

	@Override
	public boolean isNew() {
		return newObj;
	}
}