/*
 * Copyright (c) 2008-2019, XuanC.Chen (sunny.com) and contributing developers  
 * All rights reserved.
 * 
 * Redistribution and use in source and binary forms, with or
 * without modification, are permitted provided that the
 * following conditions are met:
 * 
 * * Redistributions of source code must retain the above
 * copyright notice, this list of conditions and the following
 * disclaimer.
 * 
 * * Redistributions in binary form must reproduce the above
 * copyright notice, this list of conditions and the following
 * disclaimer in the documentation and/or other materials
 * provided with the distribution.
 * 
 * * Neither the name of sunny nor the names of its
 * contributors may be used to endorse or promote products
 * derived from this software without specific prior written
 * permission.
 * 
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND
 * CONTRIBUTORS "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES,
 * INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF
 * MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT
 * NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION)
 * HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR
 * OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package com.sunny.plugin.agent;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@EqualsAndHashCode
@ToString(exclude = {"majorVersion", "minorVersion"})
public class AgentVersion implements Comparable<AgentVersion> {

	@Getter String version;
	@Getter String majorVersion;
	@Getter String minorVersion;

	public int compareTo(AgentVersion other) {
		if (other == null) {
			return 1;
		}
		String[] versionParts = version.split("\\.");
		String[] otherVersionParts = other.version.split("\\.");

		for (int i = 0; i < Math.min(versionParts.length, otherVersionParts.length); i++) {
			if (versionParts[i].length() == otherVersionParts[i].length()) {
				int comparisonResult = versionParts[i].compareTo(otherVersionParts[i]);
				if (comparisonResult == 0) {
					continue;
				} else {
					return comparisonResult;
				}
			} else {
				return versionParts[i].length() > otherVersionParts[i].length() ? 1 : -1;
			}
		}

		if (versionParts.length > otherVersionParts.length) {
			return 1;
		} else if (versionParts.length < otherVersionParts.length) {
			return -1;
		} else {
			return 0;
		}
	}

}
